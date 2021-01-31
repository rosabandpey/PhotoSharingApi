package com.Rosa.PhotoSharingApi.service;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.Rosa.PhotoSharingApi.model.Post;
import com.Rosa.PhotoSharingApi.model.AppUser;

public interface PostService {

	public Post savePost(AppUser user,HashMap<String,String> request,String postImageName);
	
	public List<Post> getPostList();
	
	public List<Post> findPostByUsername(String username);
	
	public Post getPostById(Long id);
	
	public Post deletePost(Post post);
	
	public String savePostImage(HttpServletRequest request,String filename);
	
}
