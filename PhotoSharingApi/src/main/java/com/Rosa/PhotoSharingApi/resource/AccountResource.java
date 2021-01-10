package com.Rosa.PhotoSharingApi.resource;

import java.util.HashMap;
import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

	@Autowired
	AccountService accountService;
	
	
	
	
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
}
