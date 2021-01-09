package com.Rosa.PhotoSharingApi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Rosa.PhotoSharingApi.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

	public Role findRoleByName(String name);
	
}
