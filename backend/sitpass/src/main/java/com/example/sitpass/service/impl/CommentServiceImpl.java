package com.example.sitpass.service.impl;

import com.example.sitpass.dto.CommentDTO;
import com.example.sitpass.model.*;
import com.example.sitpass.repository.CommentRepository;
import com.example.sitpass.repository.ReviewRepository;
import com.example.sitpass.repository.UserRepository;
import com.example.sitpass.service.CommentService;
import com.example.sitpass.service.ManagesService;
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
  @Autowired
  private ReviewRepository reviewRepository;


  @Autowired
  private ManagesService managesService;

  public Comment addComment(CommentDTO commentDTO, Long reviewId, Long userId) {
    Comment comment = new Comment();
    if (commentDTO.getParentCommentId() != null) {
      comment.setParentComment(commentRepository.findById(commentDTO.getParentCommentId()).get());
    }
    comment.setText(commentDTO.getText());
    comment.setCreatedAt(LocalDateTime.now());
    if (commentDTO.getReviewId() != null) {
      Set<Comment> commentEntities = commentDTO.getReplies().stream()
        .map(commentDto -> commentRepository.findById(commentDto.getId()).orElseThrow(() -> new RuntimeException("Comment not found")))
        .collect(Collectors.toSet());
      comment.setReplies(commentEntities);
    }
    comment.setReview(reviewService.getReviewById(reviewId));
    comment.setUser(userService.findById(userId));
    return this.commentRepository.save(comment);
  }

  public List<Comment> getCommentsByReviewId(Long reviewId) {
    return commentRepository.findByReviewId(reviewId);
  }

  public Comment getCommentById(Long commentId) {
    return commentRepository.findById(commentId).orElseThrow(() -> new RuntimeException("Comment not found"));
  }

  @Override
  public Comment addReplyComment(CommentDTO commentDTO, Long reviewId, Long userId) {

    if(commentDTO.getParentCommentId() == null) {
      throw new RuntimeException("Comment parent comment id is null");
    }

    if(!hasRightToReply(userId, reviewId, commentDTO.getParentCommentId())){
      throw new RuntimeException("Korisnik nema pravo da doda reply na ovaj komentar");
    }

    Comment comment = this.addComment(commentDTO, reviewId, userId);
    Comment parentComment = commentRepository.findById(commentDTO.getParentCommentId()).get();
    Set<Comment> parentCommentReplies = parentComment.getReplies();
    parentCommentReplies.add(comment);
    parentComment.setReplies(parentCommentReplies);
    return this.commentRepository.save(parentComment);
  }

  @Override
  public void deleteCommentById(Long commentId) {
    Comment comment = commentRepository.findById(commentId)
      .orElseThrow(() -> new RuntimeException("Comment not found"));

    Review review = reviewService.getReviewById(comment.getReview().getId());
    review.setComment(null);
    reviewRepository.save(review);

    commentRepository.delete(comment);
  }

  @Override
  public boolean hasRightToReply(Long userId, Long reviewId, Long commentId) {
    try {
      Comment parentComment = this.getCommentById(commentId);
      String userRole = userService.findById(userId).getRoles().get(0).getName();
      Facility facility = reviewService.getReviewById(reviewId).getFacility();
      return userRole.equals("ADMIN")||parentComment.getUser().getId().equals(userId) || userRole.equals("MANAGER") && managesService.hasRightsToFacility(userId, facility.getId());
    } catch (RuntimeException e) {
      String userRole = userService.findById(userId).getRoles().get(0).getName();
      Facility facility = reviewService.getReviewById(reviewId).getFacility();
      return (userRole.equals("MANAGER") && managesService.hasRightsToFacility(userId, facility.getId()))||userRole.equals("ADMIN");
    }
  }
}
