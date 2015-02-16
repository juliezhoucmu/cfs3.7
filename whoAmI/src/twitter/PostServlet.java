package twitter;


import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import twitter4j.Status;
import twitter4j.StatusUpdate;
import twitter4j.Twitter;
import twitter4j.TwitterException;

public class PostServlet extends HttpServlet {
    private static final long serialVersionUID = 2132731135996613711L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        StatusUpdate statusUpdate = new StatusUpdate(
                //your tweet or status message
                "#WhoIsThis??");
        
        String text = "#WhoIsThis?" + request.getParameter("text");
        String Action = request.getParameter("post");
        if (Action.equals("Give up!")) {
        	System.out.println("Getting action: Give up!");
        	 
        }
        
        
        Twitter twitter = (Twitter)request.getSession().getAttribute("twitter");
        try {
			statusUpdate.setMedia(
			        //title of media
			        "Pic"
			        , new URL("http://991kissfm.com/wp-content/uploads/2014/08/katy-perry-press-2013-650.jpg").openStream());
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
 
        //tweet or update status
        Status status;
		try {
			status = twitter.updateStatus(statusUpdate);
			
			System.out.println("status:" + status);
		
			
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		request.setAttribute(
				"msg",
				"You had successfully post the game to your twitter, please wait for your friends reply!");
        response.sendRedirect(request.getContextPath()+ "/");
        
        
        
    }
}
