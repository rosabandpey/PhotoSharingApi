package com.Rosa.PhotoSharingApi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.Rosa.PhotoSharingApi.model.User;

public interface AccountRepository extends JpaRepository<User, Long> {

	@Query("SELECT p FROM Post p WHERE p.username=:username ORDER BY p.postDate DESC")
    public User findByUsername(String username);
	
	public User findByEmail(String email);
	
	public User findByUserId(Long id);
	
	public List<User> findByUser(String username);
	
}
