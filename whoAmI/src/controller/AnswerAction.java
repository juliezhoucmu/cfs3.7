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
import org.mybeans.form.FormBeanFactory;

import twitter.CheckReply;
import twitter4j.Status;
import twitter4j.StatusUpdate;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import databean.Pic;
import databean.Post;
import databean.TwitterUser;
import formbean.RequestCheckForm;

public class AnswerAction extends Action {
	private FormBeanFactory<RequestCheckForm> formBeanFactory = FormBeanFactory
			.getInstance(RequestCheckForm.class);

	private TwitterUserDAO twitterUserDAO;
	private PicDAO picDAO;
	private PostDAO postDAO;

	public AnswerAction(Model model) {
		this.twitterUserDAO = model.getTwitterUserDAO();
		this.picDAO = model.getPicDAO();
		this.postDAO = model.getPostDAO();

	}

	public String getName() {
		return "answer.do";
	}

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
				if (this.twitterUserDAO.getTwitterUser(twitter.getId()) == null) {
					TwitterUser newuser = new TwitterUser();
					newuser.setUserId(twitter.getId());
					if (text.equals(pic.getTitle())) {
						newuser.setScore(1l);
					} else {
						newuser.setScore(0l);
					}
					this.twitterUserDAO.create(newuser);
				} else {
					if (text.equals(pic.getTitle())) {
						TwitterUser user = this.twitterUserDAO
								.getTwitterUser(twitter.getId());
						user.setScore(user.getScore() + 1);
						this.twitterUserDAO.update(user);
					}
				}
			} catch (IllegalStateException | RollbackException
					| TwitterException e) {
				e.printStackTrace();
			}
		} else if (Action.equals("Ask for help!") && pic != null) {
			try {
				statusUpdate
						.setMedia("Pic", new URL(pic.getUrl()).openStream());
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
			}catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		else if (Action.equals("Give up!") && pic != null) {
			System.out.println("Getting action: Give up!");
		}

		request.setAttribute(
				"msg",
				"You had successfully post the game to your twitter, please wait for your friends reply!");
		return (request.getContextPath() + "/");

	}
}
