package com.Rosa.PhotoSharingApi.service.Impl;


import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.Rosa.PhotoSharingApi.model.Post;
import com.Rosa.PhotoSharingApi.model.User;
import com.Rosa.PhotoSharingApi.repository.PostRepository;
import com.Rosa.PhotoSharingApi.service.PostService;
import com.Rosa.PhotoSharingApi.utility.Constant;

@Service
@Transactional
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
		
		try {
			Files.deleteIfExists(Paths.get(Constant.POST_FOLDER+"/"+post.getName()+".png"));
			postRepo.deletePost(post.getId());
			return post;
		} catch (Exception e) {
			
		}
		
		
		return null;
	}

	
	@Override
	public String savePostImage(HttpServletRequest request, String filename) {
		
		MultipartHttpServletRequest multipartHttpServletRequest=(MultipartHttpServletRequest) request;
		Iterator<String>  it=multipartHttpServletRequest.getFileNames();
		MultipartFile multipartFile=multipartHttpServletRequest.getFile(it.next());
		
		try {
			
			byte[] bytes=multipartFile.getBytes();
			Path path=Paths.get(Constant.POST_FOLDER+filename+".png");
			Files.write(path, bytes, StandardOpenOption.CREATE);
			
		} catch (Exception e) {
			
			return "Error occured.Photo not saved";
		}
		
		return "Photo saved Successfully";
	}

}
