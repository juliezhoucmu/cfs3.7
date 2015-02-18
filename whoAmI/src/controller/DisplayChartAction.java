//Name:Xu Zhao
//Andrew ID:xuzhao
//Course Number:08600
//Date:12/05/2014
package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.CommentDAO;
import model.Model;
import model.PicDAO;

import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import twitter4j.Twitter;
import databean.Comment;
import databean.Pic;
import databean.questionBean;
import formbean.ListForm;


public class DisplayChartAction extends Action {
	private FormBeanFactory<ListForm> formBeanFactory = FormBeanFactory
			.getInstance(ListForm.class);

	private CommentDAO CommentDAO;
	private PicDAO picDAO;

	public DisplayChartAction(Model model) {
		picDAO = model.getPicDAO();
	CommentDAO = model.getCommentDAO();
	}

	public String getName() {
		return "displayChart.do";
	}

	public String perform(HttpServletRequest request) {
	
		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);
  		questionBean ques = (questionBean) request.getSession(false).getAttribute("question");
  	

	
		try {
			Pic pic=(Pic) picDAO.getPicByURL(ques.getUrl());
			//Comment[] CommentList = CommentDAO.getComments(pic.getTitle().trim().toLowerCase());
			String cname=pic.getTitle().trim().toLowerCase().replaceAll("\\s","");
			System.out.println("pic.getTitle().trim().toLowerCase()"+pic.getTitle().trim().toLowerCase());
			System.out.println("cname"+cname);

			Comment[] CommentList = CommentDAO.getComments(cname);
			System.out.println("CommentList.length"+CommentList.length);

			if(CommentList.length==0||CommentList==null){
				return "gameplay.do";
			}
			if(CommentList.length!=0||CommentList!=null)
			{
			
			request.setAttribute("CommentList", CommentList);
			}
			
			

		

			//HttpSession session = request.getSession();
			//session.setAttribute("CommentList", CommentList);

			return "DisplayCharts.jsp";
		} catch (RollbackException e) {
			errors.add(e.getMessage());
			return "error.jsp";
		} 
	}
}
