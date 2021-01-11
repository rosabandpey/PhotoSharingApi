package com.Rosa.PhotoSharingApi.service.Impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.HashMap;
import java.util.HashSet;

import com.Rosa.PhotoSharingApi.model.Role;
import com.Rosa.PhotoSharingApi.model.User;
import com.Rosa.PhotoSharingApi.model.UserRole;
import com.Rosa.PhotoSharingApi.repository.AccountRepository;
import com.Rosa.PhotoSharingApi.repository.RoleRepository;
import com.Rosa.PhotoSharingApi.service.AccountService;
import com.Rosa.PhotoSharingApi.utility.Constant;
import com.Rosa.PhotoSharingApi.utility.EmailConstructor;

@Service
@Transactional
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
	
	@Autowired
	private AccountService accountService;
	
	
	@Override
	@Transactional
	public User saveUser(String name, String username, String email) {
		String password = RandomStringUtils.randomAlphanumeric(10);
		String encryptedPassword = bCryptPasswordEncoder.encode(password);
		User user = new User();
		user.setPassword(encryptedPassword);
		user.setName(name);
		user.setUsername(username);
		user.setEmail(email);
		Set<UserRole> userRoles = new HashSet<>();
		userRoles.add(new UserRole(user, accountService.findUserRoleByName("USER")));
		user.setUserRoles(userRoles);
		accountRepo.save(user);
		byte[] bytes;
		try {
			bytes = Files.readAllBytes(Constant.TEMP_USER.toPath());
			String fileName = user.getId() + ".png";
			Path path = Paths.get(Constant.USER_FOLDER + fileName);
			Files.write(path, bytes);
		} catch (IOException e) {
			e.printStackTrace();
		}
		mailSender.send(emailCunstructor.contructNewUserEmail(user, password));
		return user;
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
	public Role findUserRoleByName(String role) {
		
		return roleRepo.findRoleByName(role);
	}

	@Override
	public User updateUser(User user,HashMap<String, String> request) {
		
			
		String name = request.get("name");
		// String username = request.get("username");
		String email = request.get("email");
		String bio = request.get("bio");
		user.setName(name);
		// appUser.setUsername(username);
		user.setEmail(email);
		user.setBio(bio);
		accountRepo.save(user);
		mailSender.send(emailCunstructor.constructUpdateUserProfileEmail(user));
		return user;

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
	public User simpleSave(User user) {
		
		accountRepo.save(user);
		mailSender.send(emailCunstructor.constructUpdateUserProfileEmail(user));
		return user;
		
	}

	@Override
	public void updateUserPassword(User user, String newpassword) {

		String encryptedPassword = bCryptPasswordEncoder.encode(newpassword);
		user.setPassword(encryptedPassword);
		accountRepo.save(user);
		mailSender.send(emailCunstructor.constructResetPasswordEmail(user, newpassword));
		
	}

	@Override
	public String saveUserImage(HttpServletRequest request, long userImageId) {
	
		MultipartHttpServletRequest multipartHttpServletRequest=(MultipartHttpServletRequest) request;
		Iterator<String>  it=multipartHttpServletRequest.getFileNames();
		MultipartFile multipartFile=multipartHttpServletRequest.getFile(it.next());
		
		try {
			Files.deleteIfExists(Paths.get(Constant.USER_FOLDER+"/"+userImageId+".png"));
			byte[] bytes=multipartFile.getBytes();
			Path path=Paths.get(Constant.USER_FOLDER+userImageId+".png");
			Files.write(path, bytes);
			
		} catch (Exception e) {
			
			return "Error occured.Photo not saved";
		}
		
		return "Photo saved Successfully";
		
	}




	@Override
	public List<User> userListByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}




	
}
