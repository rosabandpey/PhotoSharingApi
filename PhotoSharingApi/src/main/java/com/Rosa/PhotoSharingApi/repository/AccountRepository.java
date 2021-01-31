package com.Rosa.PhotoSharingApi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.Rosa.PhotoSharingApi.model.AppUser;

public interface AccountRepository extends JpaRepository<AppUser, Long> {

	
    public AppUser findByUsername(String username);
	
	public AppUser findByEmail(String email);
	
	@Query("SELECT appUser FROM AppUser appUser WHERE appUser.id=:id")
	public AppUser findByUserId(@Param("id")Long id);
	
	public List<AppUser> findByUsernameContaining(String username);
	
}
