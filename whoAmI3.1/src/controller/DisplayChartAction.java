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

import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import databean.Comment;
import databean.Fund;
import formbean.ListForm;


public class DisplayChartAction extends Action {
	private FormBeanFactory<ListForm> formBeanFactory = FormBeanFactory
			.getInstance(ListForm.class);

	private CommentDAO CommentDAO;

	public DisplayChartAction(Model model) {
	CommentDAO = model.getCommentDAO();
	}

	public String getName() {
		return "displayChart.do";
	}

	public String perform(HttpServletRequest request) {
	
		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);
		

	
		try {
			Comment[] CommentList = CommentDAO.getComments();
			if(CommentList.length!=0)
			{
			
			request.setAttribute("CommentList", CommentList);
			}
			
			

		

			HttpSession session = request.getSession();
			session.setAttribute("CommentList", CommentList);

			return "DisplayCharts.jsp";
		} catch (RollbackException e) {
			errors.add(e.getMessage());
			return "error.jsp";
		} 
	}
}
