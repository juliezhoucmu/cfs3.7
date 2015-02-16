package twitter;

import java.util.ArrayList;
import java.util.List;

import model.FriendHelpDAO;
import model.Model;
import model.PicDAO;
import model.PostDAO;
import model.TwitterUserDAO;

import org.genericdao.RollbackException;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.User;
import databean.FriendHelp;
import databean.Post;
import databean.TwitterUser;

public class BackEndCheck implements Runnable {

	private static final long serialVersionUID = -6205814293093350242L;
	Twitter twitter;
	Model model;
	PostDAO postDAO;
	FriendHelpDAO friendHelpDAO;
	TwitterUserDAO userDAO;
	PicDAO picDAO;
	private static int count = 0;

	public BackEndCheck(Twitter twitter, Model model) {
		this.twitter = twitter;
		this.model = model;
		friendHelpDAO = model.getFriendHelpDAO();
		postDAO = model.getPostDAO();
		userDAO = model.getTwitterUserDAO();
		picDAO = model.getPicDAO();

	}

	public void run() {
		try {
			TwitterUser user = userDAO.getTwitterUser(twitter.getId());
			while (true) {
				if (count > 10) {
					Thread.sleep(15*1000 *60);
					System.out.println("Getting close to rate limit, sleep for a while");
					count = 0;
				}
				count++;
				System.out.println("Going to check");
				Post[] postlist = postDAO.getPostByUser(twitter.getId());
				CheckReply cr = new CheckReply();
				for (int i = 0; i < postlist.length; i++) {
					Post post = postDAO.getPost(postlist[i].getTwitId());
					if (post == null || post.getResolved() == true) {
						continue;
					}
					System.out
							.println("Going to check post" + post.getAnswer());
					Status correctReply = cr.getCorrectAnswer(
							postlist[i].getTwitId(), postlist[i].getAnswer(),
							twitter);
					if (correctReply != null) {

						FriendHelp newhelp = new FriendHelp();
						System.out.println("I'm going to set owner id as " + twitter.getId());
						newhelp.setOwnerId(twitter.getId());
						newhelp.setAnswer(correctReply.getText());
						newhelp.setFriend("@"
								+ correctReply.getUser().getScreenName());
						newhelp.setPicUrl(picDAO.getPic(postlist[i].getPicId())
								.getUrl());
						user.setScore(user.getScore() + 1);
						postlist[i].setResolved(true);
						postDAO.update(postlist[i]);
						userDAO.update(user);
						friendHelpDAO.createAutoIncrement(newhelp);
					}
					Thread.sleep(1000 * 10);
				}
			}

		} catch (RollbackException | IllegalStateException | TwitterException
				| InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
