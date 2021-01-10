package com.Rosa.PhotoSharingApi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.Rosa.PhotoSharingApi.model.User;

public interface AccountRepository extends JpaRepository<User, Long> {

	
    public User findByUsername(String username);
	
	public User findByEmail(String email);
	
	@Query("SELECT user FROM User user WHERE user.id=:id ")
	public User findByUserId(@Param("id")Long id);
	
	public List<User> findByUser(String username);
	
}
