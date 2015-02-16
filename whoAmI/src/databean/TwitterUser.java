package databean;

import org.genericdao.PrimaryKey;

@PrimaryKey("userId")
public class TwitterUser {
	private long userId;
	private long score;
	
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
}
