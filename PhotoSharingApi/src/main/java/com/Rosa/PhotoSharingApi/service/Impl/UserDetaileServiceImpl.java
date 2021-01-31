package com.Rosa.PhotoSharingApi.service.Impl;



import java.util.HashSet;

import javax.transaction.Transactional;

import  java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


import com.Rosa.PhotoSharingApi.model.AppUser;
import com.Rosa.PhotoSharingApi.model.UserRole;
import com.Rosa.PhotoSharingApi.repository.AccountRepository;


public class UserDetaileServiceImpl implements UserDetailsService {

	
	
	
	
	@Autowired
	private AccountRepository accountRepo;
	
	@Transactional
	@Override
	public UserDetails loadUserByUsername(String username)  
				throws UsernameNotFoundException {
		AppUser user = accountRepo.findByUsername(username);
		
		if(user==null) {
		throw new UsernameNotFoundException(
				"User '" + username + "' not found");
		}
		
		Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
		Set<UserRole> userRoles = user.getUserRoles();
	    for (UserRole  role : userRoles) {
	        grantedAuthorities.add(new SimpleGrantedAuthority(role.getRole().toString()));
	    }
	    return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthorities);
	    
		
		}
		
	}


