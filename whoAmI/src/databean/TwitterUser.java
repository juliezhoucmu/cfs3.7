package databean;

import java.io.Serializable;

import org.genericdao.PrimaryKey;

@PrimaryKey("userId")
public class TwitterUser implements Serializable{
	private static final long serialVersionUID = 1L;
	private long userId;
	private long score;
	private String screenName;
	private String userName;
	private String profileImgUrl;
	
	public long getUserId() {
		return this.userId;
	}
	
	public void setUserId(long userId) {
		this.userId = userId;
	}
	
	public long getScore() {
		return this.score;
	}
	
	public void setScore(long score) {
		this.score = score;
	}

	public String getScreenName() {
		return screenName;
	}

	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getProfileImgUrl() {
		return profileImgUrl;
	}

	public void setProfileImgUrl(String profileImgUrl) {
		this.profileImgUrl = profileImgUrl;
	}
}
