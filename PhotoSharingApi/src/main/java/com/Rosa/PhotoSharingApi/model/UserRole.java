package com.Rosa.PhotoSharingApi.model;

public class UserRole {

	
	private long UserIdRole;
	private User user;
	private Role role;
	
	
	public UserRole(long userIdRole, User user, Role role) {
		
		UserIdRole = userIdRole;
		this.user = user;
		this.role = role;
	}


	public UserRole() {
	}


	public long getUserIdRole() {
		return UserIdRole;
	}


	public void setUserIdRole(long userIdRole) {
		UserIdRole = userIdRole;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public Role getRole() {
		return role;
	}


	public void setRole(Role role) {
		this.role = role;
	}
	
	
	
	
	
	
	
	
}
