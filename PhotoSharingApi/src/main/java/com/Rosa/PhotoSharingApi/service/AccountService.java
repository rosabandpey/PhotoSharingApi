package com.Rosa.PhotoSharingApi.service;

import java.util.List;

import com.Rosa.PhotoSharingApi.model.Role;
import com.Rosa.PhotoSharingApi.model.User;

public interface AccountService {
	
	
	public void saveUser(User user);
	
	public User findByUsername(String username);
	
	public User findByUserEmail(String email);
	
	public User findByUserId(Long id);
	
	public List<User> userList();
	
	public Role saveRole(Role role);
	
	public Role findByUserRoleByName(String role);
	
	public void updateUser(User user);
	
	public void deleteUser(User user);
	
	public void resetPassword(User user);
	
	public List<User> userListByUsername(String username);
	
	public User simpleSave(User user);
	
	
}
