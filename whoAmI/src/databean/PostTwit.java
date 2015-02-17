package databean;

import java.io.Serializable;

import org.genericdao.PrimaryKey;

@PrimaryKey("twitId")
public class PostTwit implements Serializable{
	private static final long serialVersionUID = 1L;

	private long twitId;
	private long userId;
	private long picId;
	private String profileImageUrl;
	private String screenName;
	private String userName;
	private String picUrl;
	private String twitUrl;
	private String userUrl;

	public long getTwitId() { 	return twitId;}
	public void setTwitId(long twitId) { this.twitId = twitId;}

	public long getUserId() {	return userId;}
	public void setUserId(long userId) {this.userId = userId;}
	
	public long setPicId() {	return picId;}
	public void setPicId(long picId) {this.picId = picId;}

	
	public String getProfileImageUrl() {	return profileImageUrl;}
	public void setProfileImageUrl(String profileImageUrl) {	this.profileImageUrl = profileImageUrl;}

	public String getScreenName() {	return screenName;}
	public void setScreenName(String screenName) {	this.screenName = screenName;}

	
	public String getPicUrl() {	return picUrl;}
	public void setPicUrl(String picUrl) {	this.picUrl = picUrl;}
	public String getTwitUrl() {
		return twitUrl;
	}
	public void setTwitUrl(String twitUrl) {
		this.twitUrl = twitUrl;
	}
	public String getUserUrl() {
		return userUrl;
	}
	public void setUserUrl(String userUrl) {
		this.userUrl = userUrl;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}

	
}
