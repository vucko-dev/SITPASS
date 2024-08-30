package com.example.sitpass.service.impl;

import com.example.sitpass.dto.CommentDTO;
import com.example.sitpass.model.Comment;
import com.example.sitpass.model.Discipline;
import com.example.sitpass.repository.CommentRepository;
import com.example.sitpass.repository.ReviewRepository;
import com.example.sitpass.repository.UserRepository;
import com.example.sitpass.service.CommentService;
import com.example.sitpass.service.ReviewService;
import com.example.sitpass.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

  @Autowired
  private CommentRepository commentRepository;

  @Autowired
  private UserService userService;

  @Autowired
  private ReviewService reviewService;


//  Comment addComment(CommentDTO commentDTO);
//  List<Comment> getCommentByReviewId(Long reviewId);
//  Comment getCommentById(Long commentId);

  public Comment addComment(CommentDTO commentDTO) {
    Comment comment = new Comment();
    comment.setParentComment(commentRepository.findById(commentDTO.getParentCommentId()).get());
//    comment.setId(commentDTO.getId());
    comment.setText(commentDTO.getText());
    comment.setCreatedAt(LocalDateTime.now());
//    comment.setReplies(commentDTO.getReplies());
    Set<Comment> commentEntities = commentDTO.getReplies().stream()
      .map(commentDto -> commentRepository.findById(commentDto.getId()).orElseThrow(() -> new RuntimeException("Comment not found")))
      .collect(Collectors.toSet());
    comment.setReplies(commentEntities);
    comment.setReview(reviewService.getReviewById(commentDTO.getReviewId()));
    comment.setUser(userService.findById(commentDTO.getUserId()));
    return this.commentRepository.save(comment);
  }

  public List<Comment> getCommentsByReviewId(Long reviewId) {
    return commentRepository.findByReviewId(reviewId);
  }

  public Comment getCommentById(Long commentId) {
    return commentRepository.findById(commentId).orElseThrow(() -> new RuntimeException("Comment not found"));
  }


}
