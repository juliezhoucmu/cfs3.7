//Hua-Ming Lee
//huamingl
//08-600
//hw9
//2014/12/1

package controller;

import java.io.IOException;
import java.util.Calendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.CommentDAO;
import model.CustomerDAO;
import model.EmployeeDAO;
import model.FundDAO;
import model.Fund_Price_HistoryDAO;
import model.Model;
import model.PositionDAO;
import model.TransactionDAO;

import org.genericdao.RollbackException;

import databean.Comment;
import databean.Customer;
import databean.Employee;
import databean.Fund;
import databean.Fund_Price_History;
import databean.Position;
import databean.TransactionBean;

public class Controller extends HttpServlet {

	private static final long serialVersionUID = 1L;
	int i=0;
	public void init() throws ServletException {
		Model model = new Model(getServletConfig());
		
		Action.add(new LoginAction(model));
		Action.add(new CustomerManageAction(model));
		Action.add(new ViewAccount(model));
		Action.add(new LogoutAction(model));
		Action.add(new ChangePwdAction(model));
		Action.add(new SellFundAction(model));
		Action.add(new BuyFundAction(model));
		Action.add(new ResearchFundAction(model));
		Action.add(new ListAction(model));
		Action.add(new History(model));
		Action.add(new RequestCheckAction(model));
		Action.add(new CustomerSearchAction(model));
		Action.add(new GameplayAction(model));
		Action.add(new DisplayChartAction(model));
		
		
		//precompute sentiment analysis and store in db
		SentimentAnalysis initialise=new SentimentAnalysis();
		try {
			initialise.sendPostRequest(model.getCommentDAO());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		initializeTable(model.getCustomerDAO(), 
				model.getEmployeeDAO(),
				model.getTransactionDAO(), 
				model.getFundDAO(),
				model.getPositionDAO(),
				model.getFund_Price_HistoryDAO(),
				model.getCommentDAO()

				
				);
	
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
		HttpSession session = request.getSession(true);
		String servletPath = request.getServletPath();
		Customer customer = (Customer) session.getAttribute("customer");
		Employee employee = (Employee) session.getAttribute("employee");

		String action = getActionName(servletPath);

		// System.out.println("servletPath="+servletPath+" requestURI="+request.getRequestURI()+"  user="+user);

		if (action.equals("register.do") || action.equals("login.do")
				|| action.equals("list.do") || action.equals("update.do") || action.equals("displayChart.do")) {
			// Allow these actions without logging in
			return Action.perform(action, request);
		}

		if (customer == null && employee == null) {
			// If the user hasn't logged in, direct him to the login page
			return Action.perform("login.do", request);
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

		throw new ServletException(Controller.class.getName()
				+ ".sendToNextPage(\"" + nextPage + "\"): invalid extension.");
	}

	private String getActionName(String path) {
		int slash = path.lastIndexOf('/');
		return path.substring(slash + 1);
	}
	
	public void initializeTable(CustomerDAO customerDAO, EmployeeDAO employeeDAO, TransactionDAO transactionDAO,FundDAO fundDAO,PositionDAO positionDAO, 
			Fund_Price_HistoryDAO fundPriceHistoryDAO, CommentDAO commentDAO) {

		try {
			Employee admin = employeeDAO.read("admin");
			if (admin == null) {
				admin = new Employee();
				admin.setUsername("admin");
				admin.setPassword("passw0rd");
				employeeDAO.create(admin);
			}
		} catch (RollbackException e) {
			e.printStackTrace();
		}
	
	
		try {
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
		
		
	}
	
}
