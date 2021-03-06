package com.Rosa.PhotoSharingApi.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;


@Entity
public class Post  implements Serializable {

	
	private static final long serialVersionUID = -8049727227696194101L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(updatable=false,nullable=false)
	private Long  id;
	private String name;
	
	@Column (columnDefinition="text")
	private String caption;
	private String location;
	private int likes;
	private Date postDate;
	private Long  userImageId;
	private String username;
	
	@OneToMany(cascade=CascadeType.ALL ,fetch=FetchType.LAZY)
	@JoinColumn(name="post_id")
	private List<Comment> commentList;
	
	public Post(Long  id, String name, String caption, String location, int likes, Date postDate,String username, Long  userImageId,
			List<Comment> commentList) {
				this.id = id;
		this.name = name;
		this.caption = caption;
		this.location = location;
		this.likes = likes;
		this.postDate = postDate;
		this.username=username;
		this.userImageId = userImageId;
		this.commentList = commentList;
	}
	
	public Post() {
		
	}
	
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Long  getId() {
		return id;
	}
	public void setId(Long  id) {
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
	public Long  getUserImageId() {
		return userImageId;
	}
	public void setUserImageId(Long  userImageId) {
		this.userImageId = userImageId;
	}
	public List<Comment> getCommentList() {
		return commentList;
	}
	public void setCommentList(Comment comment) {
		this.commentList.add(comment);
	}
	
	
}
