package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.Model;
import model.PicDAO;

import org.genericdao.RollbackException;

import twitter4j.Twitter;
import databean.Pic;
import databean.questionBean;

public class FirstPage extends Action {
	private Model model;

	// constructor
	public FirstPage(Model model) {
		this.model = model;

	}

	// get action name
	public String getName() {
		return "firstpage.do";
	}

	// return next page name
	public String perform(HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		Twitter twitter = (Twitter) session.getAttribute("twitter");
		
		PicDAO picDAO =  model.getPicDAO();
		try {
			Pic[] picList = picDAO.getPics();
			if (picList.length > 0) {
				int index = (int)(Math.random() * picList.length);
				questionBean question = new questionBean();
				question.setId(picList[index].getPicId());
				question.setUrl(picList[index].getUrl());
				session.setAttribute("question", question);
			}
		} catch (RollbackException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "firstpage.jsp";
	}

}