package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.CommentDAO;
import model.Model;

import org.genericdao.RollbackException;

import twitter.BackEndCheck;
import twitter4j.Twitter;
import databean.Comment;
import flickr.getPhotos;

public class Controller extends HttpServlet {

	private static final long serialVersionUID = 1L;
	int i = 0;
	private static boolean checkingReply;
	public Model model;
	

	public void init() throws ServletException {
		checkingReply = false;
		model = new Model(getServletConfig());
		Action.add(new LoginAction(model));
		Action.add(new GameplayAction(model));
		Action.add(new DisplayChartAction(model));
		Action.add(new FirstPage(model));
		Action.add(new AnswerAction(model));
		Action.add(new viewScore(model));
		

		// precompute sentiment analysis and store in db
		SentimentAnalysis initialise = new SentimentAnalysis();
		try {
			initialise.sendPostRequest(model.getCommentDAO());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		initializeTable(model);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String nextPage = performTheAction(request);
		sendToNextPage(nextPage, request, response);
	}

	private String performTheAction(HttpServletRequest request) {
		String servletPath = request.getServletPath();
		String action = getActionName(servletPath);
		
		if (action.equals("login.do")) {
			return Action.perform(action, request);
		}
		
		HttpSession session = request.getSession(true);
		Twitter twitter = (Twitter) session.getAttribute("twitter");
		
		if (twitter == null) {
			System.out.println("redirecting to login.do");
			return "login.do";
		}

		System.out.println("checkingReply = " + checkingReply);
		// start to check in the backend
		if (!checkingReply) {
			BackEndCheck beCheck = new BackEndCheck(twitter,model);
			Thread t = new Thread(beCheck);
			t.start();
			checkingReply = true;
		}
		
		// Let the logged in user run his chosen action
		return Action.perform(action, request);
	}

	private void sendToNextPage(String nextPage, HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		if (nextPage == null) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND,
					request.getServletPath());
			return;
		}

		if (nextPage.endsWith(".do")) {
			response.sendRedirect(nextPage);
			return;
		}

		if (nextPage.endsWith(".jsp")) {
			RequestDispatcher d = request.getRequestDispatcher("WEB-INF/"
					+ nextPage);
			d.forward(request, response);
			return;
		}
		response.sendRedirect(nextPage);

	}

	private String getActionName(String path) {
		int slash = path.lastIndexOf('/');
		return path.substring(slash + 1);
	}

	public void initializeTable(Model model)  {
		try {
			CommentDAO commentDAO = model.getCommentDAO();
			Comment comment1 = commentDAO.getComments("kp");
			if (comment1 == null) {
				comment1 = new Comment();
				comment1.setName("kp");
				comment1.setPositiveCon(10);
				comment1.setNegativeCon(20);
				comment1.setNeutralCon(30);

				System.out.println(comment1.getName());
				// fundDAO.createAutoIncrement(fund1);
				commentDAO.create(comment1);
			}

			Comment comment2 = commentDAO.getComments("kp");
			if (comment2 == null) {
				comment2 = new Comment();
				comment2.setName("kp");
				comment2.setPositiveCon(10);
				comment2.setNegativeCon(20);
				comment2.setNeutralCon(30);

				System.out.println(comment1.getName());
				// fundDAO.createAutoIncrement(fund1);
				commentDAO.create(comment2);
			}

			Comment comment3 = commentDAO.getComments("kp");
			if (comment3 == null) {
				comment3 = new Comment();
				comment3.setName("kp");
				comment3.setPositiveCon(10);
				comment3.setNegativeCon(20);
				comment3.setNeutralCon(30);

				System.out.println(comment1.getName());
				// fundDAO.createAutoIncrement(fund1);
				commentDAO.create(comment3);
			}

		} catch (RollbackException e) {
			e.printStackTrace();
		}

		// fetch flickr pictures
		String a_api_key = "4222c97abc1c18a7a314993bcb28993e";
		String a_nsid = "131094040@N04";
		String a_shared_secret = "7858d27d5d8971a6";

		getPhotos gp = new getPhotos(a_api_key, a_nsid, a_shared_secret, model);
		gp.getAllPhotos();
	}
}
