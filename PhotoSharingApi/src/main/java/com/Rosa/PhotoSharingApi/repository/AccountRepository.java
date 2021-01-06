package com.Rosa.PhotoSharingApi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Rosa.PhotoSharingApi.model.User;

public interface AccountRepository extends JpaRepository<User, Long> {

	
    //public User findByUserName(String username);
	
	public User findByEmail(String email);
	
	public User findByUserId(Long id);
	
	//public List<User> findByUser(String username);
	
}
