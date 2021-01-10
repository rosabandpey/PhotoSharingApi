package com.Rosa.PhotoSharingApi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.Rosa.PhotoSharingApi.model.Post;

public interface PostRepository extends JpaRepository<Post, Long> {

	@Query("SELECT p FROM Post p ORDER BY p.postDate DESC")
	public List<Post> findAll();
	
	@Query("SELECT p FROM Post p WHERE p.username=:username ORDER BY p.postDate DESC")
	public List<Post> findByUsername(@Param("username") String username);
	
	@Query("SELECT p FROM Post p WHERE p.id=:id")
	public Post findPostById(@Param("id") long id);
	
	@Modifying
	@Query("DELETE Post WHERE id=:id")
	public void deletePost(@Param("id")long id);
	
}
