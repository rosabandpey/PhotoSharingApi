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
	private AppUser user;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "role_id")
	private Role role;
	
	
	public UserRole(long userIdRole, AppUser user, Role role) {
		
		UserIdRole = userIdRole;
		this.user = user;
		this.role = role;
	}

	public UserRole(AppUser user, Role role) {
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


	public AppUser getUser() {
		return user;
	}


	public void setUser(AppUser user) {
		this.user = user;
	}


	public Role getRole() {
		return role;
	}


	public void setRole(Role role) {
		this.role = role;
	}
	
	
	
	
	
	
	
	
}
