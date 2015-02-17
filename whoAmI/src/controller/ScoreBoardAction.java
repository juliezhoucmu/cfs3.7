package controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.Model;
import model.TwitterUserDAO;

import org.mybeans.form.FormBeanFactory;

import twitter4j.Twitter;
import databean.TwitterUser;
import formbean.GamePlayForm;

public class ScoreBoardAction extends Action{
	private FormBeanFactory<GamePlayForm> formBeanFactory = FormBeanFactory.getInstance(GamePlayForm.class);
	private TwitterUserDAO userDAO;
	
	public ScoreBoardAction(Model model) {
		userDAO  = model.getTwitterUserDAO();
	}

	public String getName() { return "scoreboard.do"; }
	
	 public String perform(HttpServletRequest request) {
	        HttpSession session = request.getSession(false);
	        Twitter twitter = (Twitter)session.getAttribute("twitter");
	        if (twitter == null) {
	        	return "login.do";
	        }
	        try {
	        	TwitterUser curuser = userDAO.getTwitterUser(twitter.getId());
	        	TwitterUser[] allusers = userDAO.getTwitterUsers();
	        	Arrays.sort(allusers, new UserCmp());
	        	int index = 0;
	        	for (int i = 0; i < allusers.length; i++) {
	        		if (allusers[i].getUserId() == curuser.getUserId()) {
	        			index = i;
	        			break;
	        		}
	        	}
	        	
	        	List<TwitterUser> topTen = new ArrayList<TwitterUser>();
	        	for (int i = 0; i < 10 && i < allusers.length; i++) {
	        		topTen.add(allusers[i]);
	        	}
	        	session.setAttribute("topten", topTen);
	        	int top = (int)((index + 0.0) / (allusers.length + 0.0)) * 100;
	        	session.setAttribute("top", top);
	        	session.setAttribute("beat", allusers.length - index - 1);
	        	session.setAttribute("score",curuser.getScore());
	        	
	        	return "scoreBoard.jsp";
	        }catch (Exception e) {
				return "error.jsp";
			}
	 }
	 
	 class UserCmp implements Comparator<TwitterUser> {
		 public int compare(TwitterUser a, TwitterUser b) {
			 return (int)(b.getScore() - a.getScore());
		 }
	 }
}
