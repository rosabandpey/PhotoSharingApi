package com.Rosa.PhotoSharingApi.model;

import java.util.Date;
import java.util.List;

public class Post {

	private Integer id;
	private String name;
	private String caption;
	private String location;
	private int likes;
	private Date postDate;
	private int userImageId;
	private List<Comment> commentList;
	
	public Post(int id, String name, String caption, String location, int likes, Date postDate, int userImageId,
			List<Comment> commentList) {
				this.id = id;
		this.name = name;
		this.caption = caption;
		this.location = location;
		this.likes = likes;
		this.postDate = postDate;
		this.userImageId = userImageId;
		this.commentList = commentList;
	}
	
	public Post() {
		
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCaption() {
		return caption;
	}
	public void setCaption(String caption) {
		this.caption = caption;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public int getLikes() {
		return likes;
	}
	public void setLikes(int likes) {
		this.likes = likes;
	}
	public Date getPostDate() {
		return postDate;
	}
	public void setPostDate(Date postDate) {
		this.postDate = postDate;
	}
	public int getUserImageId() {
		return userImageId;
	}
	public void setUserImageId(int userImageId) {
		this.userImageId = userImageId;
	}
	public List<Comment> getCommentList() {
		return commentList;
	}
	public void setCommentList(List<Comment> commentList) {
		this.commentList = commentList;
	}
	
	
}
