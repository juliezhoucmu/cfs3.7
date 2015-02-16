package databean;

import java.io.Serializable;


public class questionBean  implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private long id;
	private String url;
	
	public long getId() {
		return this.id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public String getUrl() {
		return this.url;
	}
	public void setUrl(String url) {
		this.url = url;
	}	
}
