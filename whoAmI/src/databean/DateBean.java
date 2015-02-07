package databean;

import org.genericdao.PrimaryKey;
@PrimaryKey("date_id")
public class DateBean {
	int date_id;
	String date;
	public int getDate_id(){return date_id;}
	public void setDate_id(int i){ date_id=i;}
	 public String getDate()                { return date;           }
	 public void   setDate(String s)           { date=s;              }

}
