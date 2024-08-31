package com.example.sitpass.mapper;

import com.example.sitpass.dto.CommentDTO;
import com.example.sitpass.model.Comment;
import com.example.sitpass.model.Discipline;
import com.example.sitpass.repository.CommentRepository;
import com.example.sitpass.service.CommentService;
import com.example.sitpass.service.ReviewService;
import com.example.sitpass.service.UserService;
import org.apache.catalina.mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;


@Component
public class CommentMapper implements MapperInterface<Comment, CommentDTO> {


  @Autowired
  private CommentService commentService;


  @Autowired
  private UserService userService;

  @Autowired
  private ReviewService reviewService;

  @Autowired
  private CommentRepository commentRepository;

  public Comment toEntity(CommentDTO commentDTO) {
    Comment comment = new Comment();
    comment.setId(commentDTO.getId());
    if(commentDTO.getParentCommentId()!=null){
      comment.setParentComment(commentService.getCommentById(commentDTO.getParentCommentId()));
    }
    comment.setUser(userService.findById(commentDTO.getUserId()));
    comment.setReview(reviewService.getReviewById(commentDTO.getReviewId()));
    if(commentDTO.getReplies()!=null){
      Set<Comment> replies = commentDTO.getReplies().stream()
        .map(commentdto -> commentRepository.findById(commentdto.getId()).orElseThrow(() -> new RuntimeException("Comment not found")))
        .collect(Collectors.toSet());
      comment.setReplies(replies);
    }
    comment.setParentComment(commentService.getCommentById(commentDTO.getParentCommentId()));
    comment.setCreatedAt(commentDTO.getCreatedAt());
    comment.setText(commentDTO.getText());
    return comment;
  }

  public CommentDTO toDto(Comment comment) {
    CommentDTO commentDTO = new CommentDTO();
    commentDTO.setId(comment.getId());
    if(comment.getParentComment() != null) {
      commentDTO.setParentCommentId(comment.getParentComment().getId());
    }
    commentDTO.setUserId(comment.getUser().getId());
    commentDTO.setCreatedAt(comment.getCreatedAt());
    commentDTO.setText(comment.getText());
    if(comment.getReplies() != null) {
      Set<CommentDTO> repliesdto = new HashSet<>();
      Set<Comment>replies = comment.getReplies();
      for(Comment replie : replies) {
        repliesdto.add(toDto(replie));
      }
      commentDTO.setReplies(repliesdto);
    }
    commentDTO.setReviewId(comment.getReview().getId());
    return commentDTO;
  }
}
