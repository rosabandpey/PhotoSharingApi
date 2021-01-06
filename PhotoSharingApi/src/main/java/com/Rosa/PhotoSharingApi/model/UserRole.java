package com.Rosa.PhotoSharingApi.model;

<<<<<<< HEAD
public class UserRole {

	
	private long UserIdRole;
	private User user;
=======
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class UserRole {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(updatable=false,nullable=false)
	private long UserIdRole;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	@JsonIgnore
	private User user;
	
	@ManyToOne(fetch=FetchType.EAGER)
>>>>>>> branch 'master' of https://github.com/rosabandpey/PhotoSharingApi.git
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
