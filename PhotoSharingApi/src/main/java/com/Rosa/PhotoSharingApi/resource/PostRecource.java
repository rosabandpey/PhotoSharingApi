package com.Rosa.PhotoSharingApi.resource;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Rosa.PhotoSharingApi.model.Comment;
import com.Rosa.PhotoSharingApi.model.Post;
import com.Rosa.PhotoSharingApi.model.User;
import com.Rosa.PhotoSharingApi.service.AccountService;
import com.Rosa.PhotoSharingApi.service.CommentService;
import com.Rosa.PhotoSharingApi.service.PostService;

@RestController
@RequestMapping("/post")
public class PostRecource {

	@Autowired
	PostService postService;
	
	@Autowired
	CommentService commentService;
	
	@Autowired
	AccountService accountService;
	
	private String postImageName;
	
	
	@GetMapping("/PostList")
	public List<Post> getPostList()
	{
		List<Post> post=postService.getPostList();
		
		return post;

	}
	
	
	
	@GetMapping("/getPostById/{postId}")
	public Post  getPostById(@PathVariable("postId") Long id)
	{
		Post post=postService.getPostById(id);
		
		return post;

	}
	
	
	@GetMapping("/getPostByUsername/{username}")
	public ResponseEntity<?>   getPostById(@PathVariable("username") String username)
	{
		User user=accountService.findByUsername(username);
		if (user==null) {
			return new ResponseEntity<>("User not Exist",HttpStatus.NOT_FOUND);
		
		}
		try {
		List<Post> post=postService.findPostByUsername(username);
		return new ResponseEntity<>(post,HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>("error accured",HttpStatus.BAD_REQUEST);
		}
	}
	
	
	
	
	@PostMapping("/savePost") 
	public ResponseEntity<?>  savePost(@RequestBody HashMap<String,String> request ){
		
		String username=request.get("username");
		User user=accountService.findByUsername(username);
		if (user==null) {
			return new ResponseEntity<>("User not Exist",HttpStatus.NOT_FOUND);
		
		}
			postImageName=RandomStringUtils.randomAlphanumeric(10);
		
		try {
			Post post=postService.savePost(user, request, postImageName);
			return new ResponseEntity<>(post,HttpStatus.CREATED);
			
		}catch (Exception e)
		{
			return new ResponseEntity<>("Error Accured",HttpStatus.BAD_REQUEST);
		}
						
	}
	
	
	
	
	@DeleteMapping("/deletePost{id}") 
	public ResponseEntity<?>  deletePost(@PathVariable("id") Long id){
		
		
		Post post=postService.getPostById(id);
		if (post==null) {
			return new ResponseEntity<>("Post not Exist",HttpStatus.NOT_FOUND);
		
		}
					
		
		try {
			postService.deletePost(post);
			return new ResponseEntity<>(post,HttpStatus.OK);
			
		}catch (Exception e)
		{
			return new ResponseEntity<>("Error Accured",HttpStatus.BAD_REQUEST);
		}
						
	}
	
	
	
	@PostMapping("/likePost") 
	public ResponseEntity<String>  likePost(@RequestBody HashMap<String,String> request ){
		
		String username=request.get("username");
		User user=accountService.findByUsername(username);
		if (user==null) {
			return new ResponseEntity<>("User not Exist",HttpStatus.NOT_FOUND);
		
		}
					
		String postId=request.get("postId");
		Post post=postService.getPostById(Long.parseLong(postId));
		if (post==null) {
			return new ResponseEntity<>("Post not Exist",HttpStatus.NOT_FOUND);
		
		}
		
		
		try {
			post.setLikes(1);
			user.setLikedPost(post);
			accountService.simpleSave(user);
			return new ResponseEntity<>("User liked the post",HttpStatus.CREATED);
			
		}catch (Exception e)
		{
			return new ResponseEntity<>("Can not like the post",HttpStatus.BAD_REQUEST);
		}
						
	}
	
	
	
	@PostMapping("/unlikePost") 
	public ResponseEntity<String>  unlikePost(@RequestBody HashMap<String,String> request ){
		
		String username=request.get("username");
		User user=accountService.findByUsername(username);
		if (user==null) {
			return new ResponseEntity<>("User not Exist",HttpStatus.NOT_FOUND);
		
		}
					
		String postId=request.get("postId");
		Post post=postService.getPostById(Long.parseLong(postId));
		if (post==null) {
			return new ResponseEntity<>("Post not Exist",HttpStatus.NOT_FOUND);
		
		}
		
		
		try {
			post.setLikes(-1);
			user.getLikedPost().remove(post);
			accountService.simpleSave(user);
			return new ResponseEntity<>("User unliked the post",HttpStatus.CREATED);
			
		}catch (Exception e)
		{
			return new ResponseEntity<>("Can not unlike the post",HttpStatus.BAD_REQUEST);
		}
						
	}
	
	
	
	@PostMapping("/comment/add") 
	public ResponseEntity<?>  addComment(@RequestBody HashMap<String,String> request ){
		
		String username=request.get("username");
		User user=accountService.findByUsername(username);
		if (user==null) {
			return new ResponseEntity<>("User not Exist",HttpStatus.NOT_FOUND);
		
		}
					
		String postId=request.get("postId");
		Post post=postService.getPostById(Long.parseLong(postId));
		if (post==null) {
			return new ResponseEntity<>("Post not Exist",HttpStatus.NOT_FOUND);
		
		}
		
		String content=request.get("content");
		Comment comment=new Comment();
		
		try {
			comment.setContent(content);
			comment.setUserName(username);
			comment.setPostedDate(new Date());
			post.setCommentList(comment);
			commentService.saveComment(comment);
			
			return new ResponseEntity<>(comment,HttpStatus.CREATED);
			
		}catch (Exception e)
		{
			return new ResponseEntity<>("Comment not Added",HttpStatus.BAD_REQUEST);
		}
						
	}
	
	
	
	
	@PostMapping("/photo/upload") 
	public ResponseEntity<?>  uploadPhoto(HttpServletRequest  request ){
		
		
		try {
			postService.savePostImage(request, postImageName);
			
			return new ResponseEntity<>("picture saved",HttpStatus.OK);
			
		}catch (Exception e)
		{
			return new ResponseEntity<>("picture not saved",HttpStatus.BAD_REQUEST);
		}
						
	}
	
	
	
}
