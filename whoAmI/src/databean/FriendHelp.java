package databean;

import java.io.Serializable;

import org.genericdao.PrimaryKey;


@PrimaryKey("id")
public class FriendHelp implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private long id;
	private long ownerId;
	private String friend;
	private String picUrl;
	private String answer;
	
	public long getId() {
		return this.id;
	}
	
	public void setId (long id) {
		this.id = id;
	}
	
	public long getOwnerId() {
		return this.ownerId;
	}
	public void setOwnerId(long ownerId) {
		this.ownerId = ownerId;
	}
	public String getFriend() {
		return this.friend;
	}
	public void setFriend(String friend) {
		this.friend = friend;
	}
	
	public String getPicUrl() {
		return this.picUrl;
	}
	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}
	
	
	public String getAnswer() {
		return this.answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	
}
