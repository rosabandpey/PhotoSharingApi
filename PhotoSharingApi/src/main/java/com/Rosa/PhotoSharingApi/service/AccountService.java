package com.Rosa.PhotoSharingApi.service;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.Rosa.PhotoSharingApi.model.Role;
import com.Rosa.PhotoSharingApi.model.AppUser;

public interface AccountService {
	
	
	public AppUser saveUser(String name,String username,String email);
	
	public AppUser findByUsername(String username);
	
	public AppUser findByUserEmail(String email);
	
	public AppUser findByUserId(Long id);
	
	public List<AppUser> userList();
	
	public Role saveRole(Role role);
	
	public Role findUserRoleByName(String role);
	
	public AppUser updateUser(AppUser user,HashMap<String, String> request);
	
	public void deleteUser(AppUser user);
	
	public void resetPassword(AppUser user);
	
	public void updateUserPassword(AppUser user,String password);
	
	public List<AppUser> userListByUsername(String username);
	
	public AppUser simpleSave(AppUser user);
	
	public String saveUserImage(HttpServletRequest request,long userImageId);
}
