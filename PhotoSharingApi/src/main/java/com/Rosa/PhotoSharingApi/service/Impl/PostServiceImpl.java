package com.Rosa.PhotoSharingApi.service.Impl;


import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import com.Rosa.PhotoSharingApi.model.Post;
import com.Rosa.PhotoSharingApi.model.User;
import com.Rosa.PhotoSharingApi.repository.PostRepository;
import com.Rosa.PhotoSharingApi.service.PostService;

public class PostServiceImpl implements PostService {
	
	
	@Autowired
	PostRepository postRepo;

	@Override
	public Post savePost(User user, HashMap<String, String> request, String postImageName) {
		
		String caption=request.get("caption");
		String location=request.get("location");
		Post post=new Post();
		post.setCaption(caption);
		post.setLocation(location);
		post.setUsername(user.getUsername());
		post.setPostDate(new Date());
		post.setUserImageId(user.getId());
		user.setPost(post);
		postRepo.save(post);
		return post;
	}

	@Override
	public List<Post> getPostList() {
		return postRepo.findAll();
	}

	@Override
	public List<Post> findPostByUsername(String username) {
		
		return postRepo.findByUsername(username);
	}

	@Override
	public Post getPostById(Long id) {
		
		return postRepo.findPostById(id);
	}

	@Override
	public Post deletePost(Post post) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String savePostImage(HttpServletRequest request, String filename) {
		// TODO Auto-generated method stub
		return null;
	}

}
