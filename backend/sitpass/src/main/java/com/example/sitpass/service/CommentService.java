package com.example.sitpass.service;

import com.example.sitpass.dto.CommentDTO;
import com.example.sitpass.model.Comment;

import java.util.List;

public interface CommentService {
  Comment addComment(CommentDTO commentDTO, Long reviewId, Long userId);
  List<Comment> getCommentsByReviewId(Long reviewId);
  Comment getCommentById(Long commentId);
  void deleteCommentById(Long commentId);
  Comment addReplyComment(CommentDTO commentDTO, Long reviewId, Long userId);
  boolean hasRightToReply(Long userId, Long reviewId, Long commentId);
}
