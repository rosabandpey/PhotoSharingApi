package com.Rosa.PhotoSharingApi.model;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class User {

	
	private Integer id;
	private String name;
	private String userName;
	private String password;
	private String email;
	private String bio;
	private Date createdDate;
	private Set<UserRole> userRoles=new HashSet<>();
	private List<Post> post;
	private List<Post> LikedPost;
	
	
	public User(int id, String name, String userName, String password, String email, String bio, Date createdDate,
			Set<UserRole> userRoles, List<Post> post, List<Post> likedPost) {
		
		this.id = id;
		this.name = name;
		this.userName = userName;
		this.password = password;
		this.email = email;
		this.bio = bio;
		this.createdDate = createdDate;
		this.userRoles = userRoles;
		this.post = post;
		LikedPost = likedPost;
	}
	
	public User() {
				
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
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getBio() {
		return bio;
	}
	public void setBio(String bio) {
		this.bio = bio;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public Set<UserRole> getUserRoles() {
		return userRoles;
	}
	public void setUserRoles(Set<UserRole> userRoles) {
		this.userRoles = userRoles;
	}
	public List<Post> getPost() {
		return post;
	}
	public void setPost(List<Post> post) {
		this.post = post;
	}
	public List<Post> getLikedPost() {
		return LikedPost;
	}
	public void setLikedPost(List<Post> likedPost) {
		LikedPost = likedPost;
	}
	
	
	
}
