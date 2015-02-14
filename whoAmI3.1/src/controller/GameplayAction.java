package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.CustomerDAO;
import model.Model;

import org.mybeans.form.FormBeanFactory;

import databean.TransactionBean;
import formbean.GamePlayForm;

public class GameplayAction extends Action{
	private FormBeanFactory<GamePlayForm> formBeanFactory = FormBeanFactory.getInstance(GamePlayForm.class);
	private CustomerDAO customerDAO;
	public GameplayAction(Model model) {
//		userDAO = model.getUserDAO();
//		favoriteDAO = model.getFavoriteDAO();
		customerDAO = model.getCustomerDAO();
	}

	public String getName() { return "gameplay.do"; }
	
	 public String perform(HttpServletRequest request) {
	        HttpSession session = request.getSession(false);
	        
	        try {
	        	
		    	GamePlayForm form = formBeanFactory.create(request);
		    	
		    	session.setAttribute("form",form);
		    	TransactionBean transbean = new TransactionBean();
				transbean.setTransaction_type(3);
				transbean.setExecute_date(null);
				session.removeAttribute("form");
				request.setAttribute(
						"msg",
						"Game Started!");
				return "GamePlay.jsp";
	        }catch (Exception e) {
				return "error.jsp";
			}
	 }
}
