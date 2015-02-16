package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.FriendHelpDAO;
import model.Model;
import model.PicDAO;
import model.PostDAO;
import model.TwitterUserDAO;

import org.mybeans.form.FormBeanFactory;

import twitter4j.Twitter;
import databean.FriendHelp;
import databean.TwitterUser;
import formbean.RequestCheckForm;

public class viewScore extends Action {
	private FormBeanFactory<RequestCheckForm> formBeanFactory = FormBeanFactory
			.getInstance(RequestCheckForm.class);

	private TwitterUserDAO twitterUserDAO;
	private PicDAO picDAO;
	private PostDAO postDAO;
	private FriendHelpDAO friendHelpDAO;

	public viewScore(Model model) {
		this.twitterUserDAO = model.getTwitterUserDAO();
		this.picDAO = model.getPicDAO();
		this.postDAO = model.getPostDAO();
		this.friendHelpDAO = model.getFriendHelpDAO();
	}

	public String getName() {
		return "viewScore.do";
	}

	public String perform(HttpServletRequest request) {
		List<String> errors = new ArrayList<String>();
		HttpSession session = request.getSession(false);
		session.setAttribute("errors", errors);

		try {

			Twitter twitter = (Twitter) session.getAttribute("twitter");
			TwitterUser user = null;
			user = twitterUserDAO.getTwitterUser(twitter.getId());
			session.setAttribute("score", user.getScore());
			
			FriendHelp[] helps = friendHelpDAO.getFriendsHelp(twitter.getId());
			if (helps.length != 0) {
				List<FriendHelp> helplist = new ArrayList<FriendHelp>();
				for (int i = 0; i < helps.length ;i++) {
					helplist.add(helps[i]);
					System.out.println(helps[i].getAnswer() + "," + helps[i].getPicUrl());
				}
				session.setAttribute("friendhelp", helplist);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "viewScore.jsp";
	}
}
