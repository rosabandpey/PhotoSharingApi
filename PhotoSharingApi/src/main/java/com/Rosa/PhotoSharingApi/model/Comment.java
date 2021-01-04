package com.Rosa.PhotoSharingApi.model;

import java.util.Date;
import java.util.List;

public class Comment {

	
	private int id;
	private String userName;
	private String content;
	private Date postedDate;
	
	
	
	public Comment() {
	}
	
	
	public Comment(int id, String userName, String content, Date postedDate) {
		
		this.id = id;
		this.userName = userName;
		this.content = content;
		this.postedDate = postedDate;
	}
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getPostedDate() {
		return postedDate;
	}
	public void setPostedDate(Date postedDate) {
		this.postedDate = postedDate;
	}
	
	
	
	
}
