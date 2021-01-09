package com.Rosa.PhotoSharingApi.service.Impl;

import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.Rosa.PhotoSharingApi.model.Role;
import com.Rosa.PhotoSharingApi.model.User;
import com.Rosa.PhotoSharingApi.repository.AccountRepository;
import com.Rosa.PhotoSharingApi.repository.RoleRepository;
import com.Rosa.PhotoSharingApi.service.AccountService;
import com.Rosa.PhotoSharingApi.utility.EmailConstructor;

public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountRepository accountRepo;
	
	@Autowired
	private RoleRepository roleRepo;
	
	@Autowired
	private EmailConstructor emailCunstructor;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private JavaMailSender mailSender;
	
	
	@Override
	public void saveUser(User user) {
		
		String password =RandomStringUtils.randomAlphanumeric(10);
		String encryptedPass=bCryptPasswordEncoder.encode(password);
		user.setPassword(encryptedPass);
		accountRepo.save(user);
		mailSender.send(emailCunstructor.contructNewUserEmail(user, password));

	}

	@Override
	public User findByUsername(String username) {
		
		return accountRepo.findByUsername(username);
	}

	@Override
	public User findByUserEmail(String email) {
		
		return accountRepo.findByEmail(email);
	}

	@Override
	public User findByUserId(Long id) {
		
		return accountRepo.findByUserId(id);
	}

	@Override
	public List<User> userList() {
		
		return accountRepo.findAll();
	}

	@Override
	public Role saveRole(Role role) {
		return roleRepo.save(role);
	}

	@Override
	public Role findByUserRoleByName(String role) {
		
		return roleRepo.findRoleByName(role);
	}

	@Override
	public void updateUser(User user) {
		
		String password =user.getPassword();
		String encryptedPass=bCryptPasswordEncoder.encode(password);
		user.setPassword(encryptedPass);
		accountRepo.save(user);
		mailSender.send(emailCunstructor.constructUpdateUserProfileEmail(user));


	}

	@Override
	public void deleteUser(User user) {
		accountRepo.delete(user);
	}

	@Override
	public void resetPassword(User user) {
		
		String password =RandomStringUtils.randomAlphanumeric(10);
		String encryptedPass=bCryptPasswordEncoder.encode(password);
		user.setPassword(encryptedPass);
		accountRepo.save(user);
		mailSender.send(emailCunstructor.constructResetPasswordEmail(user, password));


	}

	@Override
	public List<User> userListByUsername(String username) {
		
		return accountRepo.findByUser(username);
	}

	@Override
	public User simpleSave(User user) {
		
		accountRepo.save(user);
		mailSender.send(emailCunstructor.constructUpdateUserProfileEmail(user));
		return user;
		
	}

}
