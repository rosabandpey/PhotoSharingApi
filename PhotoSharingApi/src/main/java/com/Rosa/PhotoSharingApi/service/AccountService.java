package com.Rosa.PhotoSharingApi.service;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.Rosa.PhotoSharingApi.model.Role;
import com.Rosa.PhotoSharingApi.model.User;

public interface AccountService {
	
	
	public User saveUser(String name,String username,String email);
	
	public User findByUsername(String username);
	
	public User findByUserEmail(String email);
	
	public User findByUserId(Long id);
	
	public List<User> userList();
	
	public Role saveRole(Role role);
	
	public Role findUserRoleByName(String role);
	
	public User updateUser(User user,HashMap<String, String> request);
	
	public void deleteUser(User user);
	
	public void resetPassword(User user);
	
	public void updateUserPassword(User user,String password);
	
	public List<User> userListByUsername(String username);
	
	public User simpleSave(User user);
	
	public String saveUserImage(HttpServletRequest request,long userImageId);
}
