package com.Rosa.PhotoSharingApi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Rosa.PhotoSharingApi.model.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}
