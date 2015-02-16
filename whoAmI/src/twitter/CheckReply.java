package twitter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.User;

public class CheckReply {

	private static final long serialVersionUID = -6205814293093350242L;
	int correctCount;
	int points;
	private String check;

	
	public Status getCorrectAnswer(long statusID, String answer,
			Twitter twitter) throws IllegalStateException, TwitterException {
		System.out.println("status ID = " + statusID + ",answer = " + answer );
		long id = statusID;

		User user = null;
		try {
			user = twitter.verifyCredentials();
		} catch (TwitterException e) {
			System.out.println("unable to verify user's credential");
			e.printStackTrace();
		}

		List<Status> statuses = null;
		try {
			statuses = twitter.getMentionsTimeline();
		} catch (TwitterException e) {
			e.printStackTrace();
		}

		List<Status> reqList = new ArrayList<Status>();

		for (Status status1 : statuses) {
			if (status1.getInReplyToStatusId() == id) { // filtered only those
														// replies which belong
														// to a particular tweet
														// with tweet ID and
														// added them to a list
														// called reqList
				reqList.add(status1);
			}
		}
		System.out.println();
		System.out.println("Your new list of replies is: ");

		for (Status s : reqList) {
			System.out.println("@" + s.getUser().getScreenName() + " - "
					+ s.getText()); // display list of replies for a particular
									// tweet
		}
		// answer is the one which is fetched from db

		for (Status st : reqList) { // incrementing the count for every correct
									// answer
			
			check = "@" + twitter.getScreenName() + " " + answer;
			
			if (st.getText().equalsIgnoreCase(check)) {
				correctCount++; // incremented the count for every correct
								// answer
				// store the count in database ***************************
			}
		}

		System.out.println("");
		System.out.println("Checking for correct replies");
		
		for (Status st : reqList) { // checking the correct answer
			
			System.out.println("reply from twitter :" + st.getText());
			
			check = "@" + twitter.getScreenName() + " " + answer;
			
			System.out.println("String/Answer which we are comparing: "+ check);
			
			if (st.getText().equalsIgnoreCase(check)) {
				points++; // increment the points
				// and store the points in the
				// database**********************************
				return st;
			}
		}
		return null;
	}
}
