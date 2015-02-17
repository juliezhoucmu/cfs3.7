
package databean;
import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.util.Random;

import org.genericdao.PrimaryKey;

@PrimaryKey("id")
public class Comment implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private int    id;
    private String name;
    private int positiveCon;
    private int neutralCon;
	private int negativeCon;
    private String date;

	
	
	public int getPositiveCon()    { return positiveCon; }
	public int getNeutralCon()     { return neutralCon;  }
	public int getNegativeCon()     { return negativeCon;  }
	public String getName() 	{ return name;	}
	public int getId()      {return id;}
	public String getDate()   {return date;};
	


	public void setName(String s) 		  {	name      = s; }
    public void setId(int i) 	  {	id  = i; }
	public void setPositiveCon(int i)     {	positiveCon   = i; }
	public void setNeutralCon(int i)    {	neutralCon  = i; }
	public void setNegativeCon(int i)    {	negativeCon=i; }
	public void setDate(String d)      {date=d;}	
	


	

	
}
