package twitter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import twitter4j.RateLimitStatus;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.User;

public class CheckReply {

	private static final long serialVersionUID = -6205814293093350242L;
	int correctCount;
	int points;
	private String check;
	Map<String, RateLimitStatus> rateLimitStatus;

	public Status getCorrectAnswer(long statusID, String answer, Twitter twitter)
			throws IllegalStateException, TwitterException {
		System.out.println("status ID = " + statusID + ",answer = " + answer);
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
//			printlimit(twitter);
//			if (rateLimitStatus.get("/statuses/mentions_timeline")
//					.getRemaining() == 0) {
//				System.out.println("=========Going to sleep waiting for reset");				
//				Thread.sleep(1000 * rateLimitStatus.get("/statuses/mentions_timeline").getSecondsUntilReset());
//			}
//			printlimit(twitter);
			statuses = twitter.getMentionsTimeline();

			printlimit(twitter);

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
		// System.out.println();
		// System.out.println("Your new list of replies is: ");

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

//		System.out.println("Checking for correct replies");

		for (Status st : reqList) { // checking the correct answer

			System.out.println("reply from twitter :" + st.getText());

			check = "@" + twitter.getScreenName() + " " + answer;

			System.out
					.println("String/Answer which we are comparing: " + check);

			if (st.getText().equalsIgnoreCase(check)) {
				points++; // increment the points
				// and store the points in the
				// database**********************************
				return st;
			}
		}
		return null;
	}

	private void printlimit(Twitter twitter) {
		try {
			System.out.print("\n +++++++++++++++"
					+ twitter.getScreenName());
			rateLimitStatus = twitter.getRateLimitStatus();
			for (String endpoint : rateLimitStatus.keySet()) {
				if (endpoint.equals("/account/verify_credentials")) {
					RateLimitStatus status = rateLimitStatus.get(endpoint);
					System.out.print(" Endpoint: " + endpoint);
					// System.out.println(" Limit: " + status.getLimit());
					System.out.print(" Remaining: " + status.getRemaining());
					// System.out.println(" ResetTimeInSeconds: "+
					// status.getResetTimeInSeconds());
					System.out.print(" SecondsUntilReset: "
							+ status.getSecondsUntilReset() + "/n");

				}
			}
			System.out.println();
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
