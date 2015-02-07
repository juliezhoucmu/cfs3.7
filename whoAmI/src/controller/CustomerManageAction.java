

package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import model.CustomerDAO;
//import model.UserDAO;
//import model.FavoriteDAO;
import model.Model;
//import databeans.Favorite;
//import databeans.User;
import databean.Customer;
import databean.Employee;
import formbean.CustomerManageForm;

public class CustomerManageAction extends Action {
	private FormBeanFactory<CustomerManageForm> formBeanFactory = FormBeanFactory.getInstance(CustomerManageForm.class);
//	private UserDAO userDAO;
//	private FavoriteDAO favoriteDAO;
	private CustomerDAO customerDAO;
	
	// constructor
	public CustomerManageAction(Model model) {
//		userDAO = model.getUserDAO();
//		favoriteDAO = model.getFavoriteDAO();
		customerDAO = model.getCustomerDAO();
	}
	
	// get action name
	public String getName() {
		return "customermanage.do";
	}
	
	// return next page name
	public String perform(HttpServletRequest request) {
		// get session
		HttpSession session = request.getSession(false);
		CustomerManageForm form;
		
		
		try {
			form = formBeanFactory.create(request);
			
			 if (!form.isPresent()) {
		            return "customer-manage.jsp";
		        }
		        
			 if (form.getAction().equals("action")) {
				 System.out.println("action");
				 return "GamePlay.jsp";
			 }
			 return "GamePlay.jsp";
			
		} catch (FormBeanException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//prevent multiple sessions
  		Employee emp = (Employee) request.getSession(false).getAttribute("employee");
  		if(emp!=null)
  		{
  			return "login.jsp";
  		}
		
		// set title attribute
//		String title = "Manage List Page";
//		session.setAttribute("title", title);
		
		// set error attribute
		List<String> errors = new ArrayList<String>();
		session.setAttribute("errors", errors);
				
		
		// get user
		Customer customer = (Customer) session.getAttribute("customer");
		
		// get favorite list
		return "customer-manage.jsp";
	}
}
