
package formbean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import org.mybeans.form.FormBean;

public class TransitionDayForm extends FormBean{
	private String date;
	//private long price;
	private String[] price;
	private int fund_id;
	
	public int getFund_id ()			        { return fund_id; }
	public String[] getPrice ()			        { return price; }
	public String getDate ()		        	{ return date; }
	
	public void setFund_id(int s)				{ fund_id = s;    }
	public void setPrice(String[] l) 		    { price  = l;   }
	public void setDate(String d)				{ date = d;    }
	
	public List<String> getValidationErrors (String lastdate) {
		List<String> errors = new ArrayList<String>();
		System.out.println(lastdate);
		 if (date == null || date.length() == 0)
		 {
			 errors.add("Please enter the transition day date!");
		 }
		 else {
			 if(date.length()>10)
			 {
				 errors.add("enter a valid date in yyyy-MM-dd");
			 }
			 else 
			 {
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				try {
					Date date = formatter.parse(getDate());
					if(lastdate==null)
					{
						
					     
					}
					else
					{
						
						Date last=formatter.parse(lastdate);
						if(last.after(date))
						{
							errors.add("enter a date after the last transition day");
						}
						else if(lastdate.equals(getDate()))
						{
							errors.add("duplicate date entered, please enter a date after the last ended day");
						}
					}
					
			 
				} catch (Exception e) {
					errors.add("enter valid date");
					System.out.println("mid part:"+errors.size());
					e.printStackTrace();
				}
			 }
		 }
		 try {
			 if(price!=null){
				 System.out.println("nulled value ein");
				 if(price.length!=0)
			 {
				 	for(int i=0;i<price.length;i++){
						System.out.println("inside for:");
					
			        	if (Double.parseDouble(price[i])*100<=0.1) {
			        		System.out.println("long error"+price);
			        		errors.add("enter a value greater than or equal to 0.01");}
			        	else if(Double.parseDouble(price[i])*100>100000){
			        		errors.add("Enter a value less than 1000");}
			        	else{
			        		if(!checkDecimal(price[i])){
			        			errors.add("Amount should be in x.xx format(tracked to two decimal places)");}
			        			
			        		}
			        				}
			 }
		 }} catch (NumberFormatException e) {
        	 errors.add("Enter a numerical value for the price field");}
		 catch(Exception e){}
       return errors;
	}
	public boolean checkDecimal(String input) {
		Pattern p = Pattern.compile("[+-]?[0-9]+.{0,1}[0-9]{0,2}");
		return p.matcher(input).matches();
	}
	
}
