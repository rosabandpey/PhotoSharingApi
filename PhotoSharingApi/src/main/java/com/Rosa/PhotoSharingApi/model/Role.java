package com.Rosa.PhotoSharingApi.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;



@Entity
public class Role implements Serializable {

	
	private static final long serialVersionUID = -8109015272289072306L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(updatable=false,nullable=false)
	private Long  RoleId;
	
	private String name;
	
	@OneToMany(mappedBy="role",cascade=CascadeType.ALL ,fetch=FetchType.LAZY)
	private Set<UserRole>  userRole=new HashSet<>();
		
	
	public Role() {
	
	}
	
	
	public Role(Long  roleId, String name, Set<UserRole> userRole) {
	
		RoleId = roleId;
		this.name = name;
		this.userRole = userRole;
	}
	
	
	public Long  getRoleId() {
		return RoleId;
	}
	public void setRoleId(Long  roleId) {
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
