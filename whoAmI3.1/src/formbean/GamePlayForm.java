package formbean;

import org.mybeans.form.FormBean;

public class GamePlayForm  extends FormBean{
	private String pic_id;
	private String action;
	private String name;
	
	public String getPic_id ()			        { return pic_id; }
	public String getAction ()			        { return action; }
	public String getName ()		        	{ return name; }
	
	public void setPic_id(String s)				{ pic_id = s;    }
	public void setAction(String s) 		    { action  = s;   }
	public void setName(String s)				{ name = s;    }
	
	
	
}
