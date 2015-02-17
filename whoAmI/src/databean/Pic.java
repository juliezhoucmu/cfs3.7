
package databean;


import java.io.Serializable;

import org.genericdao.PrimaryKey;

@PrimaryKey("picId")
public class Pic implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private long    picId;
	private String url;
	private String title;

	
	
	public long getPicId()    { return picId; }
	public String getUrl()     { return url;  }
	public String getTitle()     { return title; }

	public void setPicId(long id) 		  {	this.picId = id; }
    public void setUrl(String url) 	  {	this.url = url.trim(); }
	public void setTitle(String title)     {	this.title = title.trim(); }


	

	
}
