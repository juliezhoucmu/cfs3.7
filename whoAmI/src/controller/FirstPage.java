package controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.Model;
import model.PicDAO;
import model.PostDAO;
import model.TwitterUserDAO;

import org.genericdao.RollbackException;

import twitter4j.Status;
import twitter4j.StatusUpdate;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.User;
import databean.Pic;
import databean.Post;
import databean.PostTwit;
import databean.TwitterUser;
import databean.questionBean;

public class FirstPage extends Action {
	private TwitterUserDAO twitterUserDAO;
	private PicDAO picDAO;
	private PostDAO postDAO;
	private Model model;
	private static int last = 0;

	// constructor
	public FirstPage(Model model) {
		this.model = model;
		this.twitterUserDAO = model.getTwitterUserDAO();
		this.picDAO = model.getPicDAO();
		this.postDAO = model.getPostDAO();

	}

	// get action name
	public String getName() {
		return "firstpage.do";
	}

	// return next page name
	public String perform(HttpServletRequest request) {
		List<String> errors = new ArrayList<String>();
		HttpSession session = request.getSession(false);
		session.setAttribute("errors", errors);

		Twitter twitter = (Twitter) session.getAttribute("twitter");
		if (twitter == null) {
			return "login.do";
		}

		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		StatusUpdate statusUpdate = new StatusUpdate(
				"#WhoIsThis? HELP ME!!! do you know who is this?");
		String Action = request.getParameter("post");
		if (Action != null) {
			String text = request.getParameter("text");
			long picID = Long.parseLong(request.getParameter("id"));
			System.out.println("Current pic id = " + picID);
			Pic pic = null;
			try {
				pic = picDAO.getPic(picID);
				System.out.println("id :" + pic.getPicId() + ",title"
						+ pic.getTitle() + ", url:" + pic.getUrl());
			} catch (RollbackException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}

			if (Action.equals("Answer") && pic != null) {
				try {
					if (text.equalsIgnoreCase(pic.getTitle())) {
						TwitterUser user = this.twitterUserDAO
								.getTwitterUser(twitter.getId());
						user.setScore(user.getScore() + 1);
						this.twitterUserDAO.update(user);
						request.setAttribute("msg", "Correct!");
					} else {
						request.setAttribute("msg", "Sorry, wrong answer!");
					}

				} catch (IllegalStateException | RollbackException
						| TwitterException e) {
					e.printStackTrace();
				}
			} else if (Action.equals("Ask for help!") && pic != null) {
				try {
					statusUpdate.setMedia("Pic",
							new URL(pic.getUrl()).openStream());

				} catch (MalformedURLException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}

				try {
					Status status = twitter.updateStatus(statusUpdate);

					if (postDAO.getPost(status.getId()) == null) {
						Post newpost = new Post();
						newpost.setTwitId(status.getId());
						newpost.setUserId(twitter.getId());
						newpost.setPicId(pic.getPicId());
						newpost.setAnswer(pic.getTitle());
						newpost.setResolved(false);
						postDAO.create(newpost);
					}

					ArrayList<PostTwit> posts = (ArrayList<PostTwit>) request
							.getSession().getAttribute("posts");
					if (posts == null) {
						System.out
								.println("------------- currently not post in session");
						posts = new ArrayList<PostTwit>();
					}
					PostTwit newpost = new PostTwit();
					newpost.setTwitId(status.getId());
					newpost.setUserId(twitter.getId());
					long picId = Long.parseLong(request.getParameter("id"));
					newpost.setPicId(picId);
					User profileuser = twitter.showUser(twitter.getId());
					newpost.setProfileImageUrl(profileuser.getProfileImageURL());
					newpost.setScreenName(twitter.getScreenName());
					pic = this.picDAO.getPic(picId);
					newpost.setPicUrl(pic.getUrl());
					posts.add(newpost);
					request.getSession().setAttribute("posts", posts);
				} catch (Exception e) {
					e.printStackTrace();
				}

				for (PostTwit pt : (ArrayList<PostTwit>) request.getSession()
						.getAttribute("posts")) {
					System.out.println("----posts---------@"
							+ pt.getScreenName() + "," + pt.getTwitId());
				}
				request.setAttribute(
						"msg",
						"You had successfully post the game to your twitter, please wait for your friends reply!");

			} else if (Action.equals("Give up!") && pic != null) {
				System.out.println("Getting action: Give up!");
				return "displayChart.do";
			}
		} else {
			request.removeAttribute("msg");
		}

		// choose another random question
		PicDAO picDAO = model.getPicDAO();
		try {
			Pic[] picList = picDAO.getPics();
			if (picList.length > 0) {
				int index = (int) (Math.random() * picList.length);
				while (index == last) {
					index = (int) (Math.random() * picList.length);
				}
				last = index;
				questionBean question = new questionBean();
				question.setId(picList[index].getPicId());
				question.setUrl(picList[index].getUrl());
				System.out.println("going to ask " + picList[index].getTitle());
				session.setAttribute("question", question);

			}
		} catch (RollbackException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "firstpage.jsp";
	}

}