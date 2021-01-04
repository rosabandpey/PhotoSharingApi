package com.Rosa.PhotoSharingApi.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Role {

	
	private int RoleId;
	private String name;
	private Set<UserRole>  userRole=new HashSet<>();
		
	
	public Role() {
	
	}
	
	
	public Role(int roleId, String name, Set<UserRole> userRole) {
	
		RoleId = roleId;
		this.name = name;
		this.userRole = userRole;
	}
	
	
	public int getRoleId() {
		return RoleId;
	}
	public void setRoleId(int roleId) {
		RoleId = roleId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Set<UserRole> getUserRole() {
		return userRole;
	}
	public void setUserRole(Set<UserRole> userRole) {
		this.userRole = userRole;
	}
	
	
}
