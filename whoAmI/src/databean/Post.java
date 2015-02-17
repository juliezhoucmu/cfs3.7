package databean;

import java.io.Serializable;

import org.genericdao.PrimaryKey;

@PrimaryKey("twitId")
public class Post implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private long twitId;
	private long userId;
	private long picId;
	private String answer;
	private boolean resolved;

	public long getTwitId() { 	return twitId;}
	public void setTwitId(long twitId) { this.twitId = twitId;}

	public long getUserId() {	return userId;}
	public void setUserId(long userId) {this.userId = userId;}

	public long getPicId() {	return picId;}
	public void setPicId(long picId) {	this.picId = picId;}

	public String getAnswer() {	return answer;}
	public void setAnswer(String answer) {	this.answer = answer;}

	public boolean getResolved() {return resolved;}
	public void setResolved( boolean resolved ) {this.resolved = resolved;}
	
	
}
