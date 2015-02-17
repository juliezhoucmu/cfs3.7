package twitter;

import java.util.Map;

import model.FriendHelpDAO;
import model.Model;
import model.PicDAO;
import model.PostDAO;
import model.TwitterUserDAO;

import org.genericdao.RollbackException;

import twitter4j.RateLimitStatus;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import databean.FriendHelp;
import databean.Post;
import databean.TwitterUser;

public class BackEndCheck extends Thread {

	private static final long serialVersionUID = -6205814293093350242L;
	Twitter twitter;
	Model model;
	PostDAO postDAO;
	FriendHelpDAO friendHelpDAO;
	TwitterUserDAO userDAO;
	PicDAO picDAO;
	Map<String, RateLimitStatus> rateLimitStatus;
	boolean check = true;

	
	public BackEndCheck(Twitter twitter, Model model) {
		this.twitter = twitter;
		this.model = model;
		friendHelpDAO = model.getFriendHelpDAO();
		postDAO = model.getPostDAO();
		userDAO = model.getTwitterUserDAO();
		picDAO = model.getPicDAO();

	}
	
	public void stopCheck() {
		this.check = false;
	}

	public void run() {
		try {
			
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~Running " + this.getId() + "~~~~~~~~~~~~~~~~~~~");
			TwitterUser user = userDAO.getTwitterUser(twitter.getId());
			while (check) {
				Thread.sleep(1000 * 5);
				Post[] postlist = postDAO.getPostByUser(twitter.getId());
				CheckReply cr = new CheckReply();

				for (int i = 0; i < postlist.length; i++) {
					Post post = postDAO.getPost(postlist[i].getTwitId());
					if (post == null || post.getResolved() == true || check == false) {
						continue;
					}
					rateLimitStatus = twitter.getRateLimitStatus();
					System.out.println(this.getId() + ":========= verify_credentials" 
							+ ",remain " + rateLimitStatus.get("/account/verify_credentials").getRemaining() 
							+ ",sleep time" + rateLimitStatus.get("/account/verify_credentials").getSecondsUntilReset()
							);
					
					System.out.println("*********************before check**********************");
					
					if (rateLimitStatus.get("/account/verify_credentials").getRemaining() <= 2) {
						System.out.println("=========Going to sleep waiting for verify_credentials reset" 
					+ ",remain " + rateLimitStatus.get("/account/verify_credentials").getRemaining() 
					+ ",sleep time" + rateLimitStatus.get("/account/verify_credentials").getSecondsUntilReset()
					);
						Thread.sleep(rateLimitStatus.get("/account/verify_credentials").getSecondsUntilReset() * 1000);
					}
					if (rateLimitStatus.get("/statuses/mentions_timeline").getRemaining() == 0) {
						System.out.println("=========Going to sleep waiting for reset"
								+ ",remain " + rateLimitStatus.get("/statuses/mentions_timeline").getRemaining()
								+ ",sleep time" + rateLimitStatus.get("/statuses/mentions_timeline").getSecondsUntilReset()
								);
						Thread.sleep(1000 * rateLimitStatus.get("/statuses/mentions_timeline").getSecondsUntilReset());
					}
					
					
					
					Status correctReply = cr.getCorrectAnswer(
							postlist[i].getTwitId(), postlist[i].getAnswer(),
							twitter);
					System.out.println("*********************after check**********************");
					
					if (correctReply != null) {

						FriendHelp newhelp = new FriendHelp();
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
				}
			}

		} catch (RollbackException | IllegalStateException | TwitterException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
