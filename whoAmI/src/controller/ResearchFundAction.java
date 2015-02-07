
package controller;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import model.Fund_Price_HistoryDAO;
import model.Model;

import org.genericdao.RollbackException;

import databean.Employee;
import databean.Fund;
import databean.Fund_Price_History;
import databean.ResearchFund;

public class ResearchFundAction extends Action {
	// private FormBeanFactory<ListForm> formBeanFactory = FormBeanFactory
	// .getInstance(ListForm.class);

	// private FundDAO fundDAO;
	private Fund_Price_HistoryDAO fundPriceHistoryDAO;

	// constructor
	public ResearchFundAction(Model model) {

		// fundDAO = model.getFundDAO();
		fundPriceHistoryDAO = model.getFund_Price_HistoryDAO();
	}

	// get action name
	public String getName() {
		return "researchFund.do";
	}

	public String perform(HttpServletRequest request) {

		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);
		// get session
		// HttpSession session = request.getSession();

		//prevent multiple sessions
  		Employee emp = (Employee) request.getSession(false).getAttribute("employee");
  		if(emp!=null)
  		{
  			return "login.jsp";
  		}
		
		Fund fund = (Fund) request.getSession(false).getAttribute("Fund");
		DecimalFormat df = new DecimalFormat("0.00");
		try {

			Fund_Price_History[] fundpricehistory = fundPriceHistoryDAO
					.getFundPrice(fund.getFund_id());

			if (fundpricehistory.length != 0) {
				ResearchFund[] researchfund = new ResearchFund[fundpricehistory.length];

				for (int i = 0; i < researchfund.length; i++) {
					Long cash = fundpricehistory[i].getPrice();
					Double price = cash.doubleValue();
					researchfund[i] = new ResearchFund();
					researchfund[i].setName(fund.getName());
					researchfund[i].setSymbol(fund.getSymbol());
					researchfund[i].setPrice(df.format(price/100));
					researchfund[i].setPrice_date(fundpricehistory[i]
							.getPrice_date());
				}
				request.setAttribute("researchfund", researchfund);
			}

		} catch (RollbackException e) {
			e.printStackTrace();
		}

		return "researchFund.jsp";
	}

}
