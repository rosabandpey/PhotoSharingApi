package com.Rosa.PhotoSharingApi.resource;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Rosa.PhotoSharingApi.model.User;
import com.Rosa.PhotoSharingApi.service.AccountService;

@RestController
@RequestMapping("/user")
public class AccountResource {

	
	private Long userImageId;
	
	@Autowired
	AccountService accountService;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	
	@GetMapping("/list")
	public ResponseEntity<?>  getUserList()
	{
		List<User> users=accountService.userList();
		if(users.isEmpty()) {
			
			return new ResponseEntity<>("user not found",HttpStatus.NOT_FOUND);
			
		}
		return new ResponseEntity<>(users,HttpStatus.OK);

	}
	
	
	@GetMapping("/{username}")
	public ResponseEntity<?>  getUserByUsername(@PathVariable("username") String username)
	{
		User user=accountService.findByUsername(username);
		if(user==null) {
			
			return new ResponseEntity<>("User not found",HttpStatus.NOT_FOUND);
			
		}
		return new ResponseEntity<>(user,HttpStatus.OK);

	}
	
	
	@GetMapping("/email")
	public ResponseEntity<?>  getUserByEmail(@PathVariable("email") String email)
	{
		User user=accountService.findByUserEmail(email);
		if(user==null) {
			
			return new ResponseEntity<>("Email not found",HttpStatus.NOT_FOUND);
			
		}
		return new ResponseEntity<>(user,HttpStatus.OK);

	}
	
	
	@PostMapping("/register") 
	public ResponseEntity<?>  registerUser(@RequestBody HashMap<String,String> request ){
		
		String username=request.get("username");
		if (accountService.findByUsername(username)!=null) {
			return new ResponseEntity<>("User Exist",HttpStatus.FOUND);
		}
		
		String email=request.get("email");
		if (accountService.findByUserEmail(email)!=null) {
			return new ResponseEntity<>("User Exist",HttpStatus.FOUND);
		}
		
		String name=request.get("name");
		
		try {
			User user=accountService.saveUser(name, username, email);
			return new ResponseEntity<>(user,HttpStatus.OK);
			
		}catch (Exception e)
		{
			return new ResponseEntity<>("Error Accured",HttpStatus.BAD_REQUEST);
		}
						
	}
	
	
	
	@PostMapping("/update") 
	public ResponseEntity<?>  updateUser(@RequestBody HashMap<String,String> request ){
		
		String id=request.get("id");
		User user=accountService.findByUserId(Long.parseLong(id));
			
			if (user==null) {
				return new ResponseEntity<>("User not Exist",HttpStatus.NOT_FOUND);
			}
		
		
		try {
			accountService.updateUser(user, request);
			return new ResponseEntity<>(user,HttpStatus.OK);
			
		}catch (Exception e)
		{
			return new ResponseEntity<>("Error Accured",HttpStatus.BAD_REQUEST);
		}
				
		}
	
	
	
	@PostMapping("/photo/upload") 
	public ResponseEntity<?>  fileUpload(HttpServletRequest request ){
		
		try {
			accountService.saveUserImage(request, userImageId);
			return new ResponseEntity<>("User Picture Saved",HttpStatus.OK);
			
		}catch (Exception e)
		{
			return new ResponseEntity<>("User Picture not Saved",HttpStatus.BAD_REQUEST);
		}
				
		}
	
	
	@PostMapping("/resetPassword/{email}") 
	public ResponseEntity<String>  resetPassword(@PathVariable("email") String email ){
		
		User user=accountService.findByUserEmail(email);
			
			if (user==null) {
				return new ResponseEntity<>("User not Exist",HttpStatus.NOT_FOUND);
			}
		
		try {
			accountService.resetPassword(user);
			return new ResponseEntity<>("Password Email Sent",HttpStatus.OK);
			
		}catch (Exception e)
		{
			return new ResponseEntity<>("Error accured",HttpStatus.BAD_REQUEST);
		}
				
		}
	
	
	
	@PostMapping("/updatePassword") 
	public ResponseEntity<String>  updatePassword(@RequestBody HashMap<String,String> request ){
		
		String username=request.get("username");
		User user=accountService.findByUsername(username);
			
			if (user==null) {
				return new ResponseEntity<>("User not Exist",HttpStatus.NOT_FOUND);
			}
		

			String currentPassword=request.get("currentPassword");
			String newPassword=request.get("newPassword");
			String confirmPassword=request.get("confirmPassword");
			
			if (!newPassword.equals(confirmPassword)) {
				return new ResponseEntity<>("Password not Matched",HttpStatus.BAD_REQUEST);
			}
			String userPassword=user.getPassword();
			try {
			if ((newPassword!=null)  && !(newPassword.isEmpty())  && (StringUtils.isEmpty(newPassword))) {
				
			   if (bCryptPasswordEncoder.matches(currentPassword, userPassword)) {
				
					accountService.updateUserPassword(user, newPassword);
					return new ResponseEntity<>("password changed Successfully",HttpStatus.OK);
					
			   }
					 else {
							return new ResponseEntity<>("Iccorect Current password",HttpStatus.BAD_REQUEST);
						}
			   
			}
			return new ResponseEntity<>("password is null",HttpStatus.BAD_REQUEST);
			}catch (Exception e)
				{
					return new ResponseEntity<>("Error accured",HttpStatus.BAD_REQUEST);
				}
			
			}
		
				
		
	
}
