package com.Rosa.PhotoSharingApi.service.Impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Rosa.PhotoSharingApi.model.Comment;
import com.Rosa.PhotoSharingApi.repository.CommentRepository;
import com.Rosa.PhotoSharingApi.service.CommentService;

@Service
@Transactional
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentRepository commentRepo;
	
	@Override
	public void saveComment(Comment comment) {
		

		commentRepo.save(comment);
		
	}

}
