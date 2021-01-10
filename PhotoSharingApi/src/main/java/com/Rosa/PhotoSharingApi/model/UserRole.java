package com.Rosa.PhotoSharingApi.model;

import java.io.Serializable;

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
public class UserRole  implements Serializable{

	
	private static final long serialVersionUID = -121838495708416186L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(updatable=false,nullable=false)
	private long UserIdRole;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	@JsonIgnore
	private User user;
	
	@ManyToOne(fetch=FetchType.EAGER)
	private Role role;
	
	
	public UserRole(long userIdRole, User user, Role role) {
		
		UserIdRole = userIdRole;
		this.user = user;
		this.role = role;
	}

	public UserRole(User user, Role role) {
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
