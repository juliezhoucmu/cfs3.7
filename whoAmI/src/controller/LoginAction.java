package controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.Model;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.RequestToken;
import databean.TwitterUser;


/*
 * Processes the parameters from the form in login.jsp.
 * If successful, set the "user" session attribute to the
 * user's User bean and then redirects to view the originally
 * requested photo.  If there was no photo originally requested
 * to be viewed (as specified by the "redirect" hidden form
 * value), just redirect to manage.do to allow the user to manage
 * his photos.
 */
public class LoginAction extends Action {
	
	
	public LoginAction(Model model) {
		
	}

	public String getName() { return "login.do"; }
    
    public String perform(HttpServletRequest request) {
    	HttpSession session = request.getSession(false);
    	Twitter user = (Twitter)session.getAttribute("twitter");
    	
		
		if (user == null) {
			System.out.println("redirecting to login.jsp");
			return "login.jsp";
		}
		
		try {
			Twitter twitter = new TwitterFactory().getInstance();
			request.getSession().setAttribute("twitter", twitter);
			
            StringBuffer callbackURL = request.getRequestURL();
            int index = callbackURL.lastIndexOf("/");
            callbackURL.replace(index, callbackURL.length(), "").append("/callback");

            RequestToken requestToken = twitter.getOAuthRequestToken(callbackURL.toString());
            request.getSession().setAttribute("requestToken", requestToken);
            System.out.println(requestToken.getAuthenticationURL());
            return(requestToken.getAuthenticationURL());

        } catch (TwitterException e) {
            e.printStackTrace();
            return "error.jsp";
        }
		
    	
		
		
		
    	
        
        
    }
}
