package com.example.sitpass.service;

import com.example.sitpass.dto.CommentDTO;
import com.example.sitpass.model.Comment;

import java.util.List;

public interface CommentService {
  Comment addComment(CommentDTO commentDTO);
  List<Comment> getCommentsByReviewId(Long reviewId);
  Comment getCommentById(Long commentId);
}
