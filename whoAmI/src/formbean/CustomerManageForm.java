package formbean;

import org.mybeans.form.FormBean;

public class CustomerManageForm extends FormBean  {
	private String action;
	
	public void setAction(String s) {	action = s;     }
	public String getAction()          {	return action;}
}
