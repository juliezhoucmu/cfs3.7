package databean;

import java.io.Serializable;

import org.genericdao.PrimaryKey;
@PrimaryKey("date_id")
public class DateBean implements Serializable{
	private static final long serialVersionUID = 1L;
	int date_id;
	String date;
	public int getDate_id(){return date_id;}
	public void setDate_id(int i){ date_id=i;}
	 public String getDate()                { return date;           }
	 public void   setDate(String s)           { date=s;              }

}
