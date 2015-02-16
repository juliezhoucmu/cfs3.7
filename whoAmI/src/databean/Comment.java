
package databean;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.util.Random;

import org.genericdao.PrimaryKey;

@PrimaryKey("id")
public class Comment {
	
	private int    id;
    private String name;
    private int positiveCon;
    private int neutralCon;
	private int negativeCon;
    private Date date;

	
	
	public int getPositiveCon()    { return positiveCon; }
	public int getNeutralCon()     { return neutralCon;  }
	public int getNegativeCon()     { return negativeCon;  }
	public String getName() 	{ return name;	}
	public int getId()      {return id;}
	public Date getDate()   {return date;};
	


	public void setName(String s) 		  {	name      = s; }
    public void setId(int i) 	  {	id  = i; }
	public void setPositiveCon(int i)     {	positiveCon   = i; }
	public void setNeutralCon(int i)    {	neutralCon  = i; }
	public void setNegativeCon(int i)    {	negativeCon=i; }
	public void setDate(Date d)      {date=d;}	
	


	

	
}
