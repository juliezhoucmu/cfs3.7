
package databean;


import java.io.Serializable;

import org.genericdao.PrimaryKey;

@PrimaryKey("name")
public class Star implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String name;
	private String twitterUrl;
	private String twitterName;
	private String screenName;
	private String profileImgURL;
	private String description;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTwitterName() {
		return twitterName;
	}
	public void setTwitterName(String twitterName) {
		this.twitterName = twitterName;
	}
	public String getScreenName() {
		return screenName;
	}
	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}
	public String getProfileImgURL() {
		return profileImgURL;
	}
	public void setProfileImgURL(String profileImgURL) {
		this.profileImgURL = profileImgURL;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getTwitterUrl() {
		return twitterUrl;
	}
	public void setTwitterUrl(String twitterUrl) {
		this.twitterUrl = twitterUrl;
	}
	
	

	
}
