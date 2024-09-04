package com.example.sitpass.controller;


import com.example.sitpass.dto.CommentDTO;
import com.example.sitpass.mapper.CommentMapper;
import com.example.sitpass.model.Comment;
import com.example.sitpass.model.User;
import com.example.sitpass.service.CommentService;
import com.example.sitpass.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {

  @Autowired
  private CommentService commentService;

  @Autowired
  private UserService userService;

  @Autowired
  private CommentMapper commentMapper;

  @PostMapping("/{id}")
  @PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'MANAGER')")
  public ResponseEntity<CommentDTO> addComment(@RequestBody CommentDTO commentDTO, @PathVariable Long id, Principal principal) {
    User user  = userService.findByUsername(principal.getName());
    Comment comment = commentService.addComment(commentDTO, id, user.getId());
    CommentDTO commentDTO2 = commentMapper.toDto(comment);
    return new ResponseEntity<>(commentDTO2, HttpStatus.CREATED);
  }

  @PostMapping("/reply/{id}")
  @PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'MANAGER')")
  public ResponseEntity<CommentDTO> addReplyComment(@RequestBody CommentDTO commentDTO, @PathVariable Long id, Principal principal) {
    if(id == 0){
      id = commentDTO.getReviewId();
    }
    User user  = userService.findByUsername(principal.getName());
    Comment comment = commentService.addReplyComment(commentDTO, id, user.getId());
    CommentDTO commentDTO2 = commentMapper.toDto(comment);
    return new ResponseEntity<>(commentDTO2, HttpStatus.CREATED);
  }

  @GetMapping("/right/{reviewId}/{commentId}")
  @PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'MANAGER')")
  public ResponseEntity<Boolean> hasRight(@PathVariable Long reviewId, @PathVariable Long commentId, Principal principal) {
    User user  = userService.findByUsername(principal.getName());
    Boolean ans = commentService.hasRightToReply(user.getId(), reviewId, commentId);
    return new ResponseEntity<>(ans, HttpStatus.OK);
  }


  @GetMapping("/review/{id}")
  @PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'MANAGER')")
  public ResponseEntity<List<CommentDTO>> getCommentsByReviewId(@PathVariable Long id) {
    List<Comment> comments = commentService.getCommentsByReviewId(id);
    List<CommentDTO> commentsDTO = new ArrayList<>();
    for (Comment comment : comments) {
      commentsDTO.add(commentMapper.toDto(comment));
    }
    return new ResponseEntity<>(commentsDTO, HttpStatus.OK);
  }

  @GetMapping("/{id}")
  @PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'MANAGER')")
  public ResponseEntity<CommentDTO> getCommentById(@PathVariable Long id) {
    Comment comment = commentService.getCommentById(id);
    CommentDTO commentDTO = commentMapper.toDto(comment);
    return new ResponseEntity<>(commentDTO, HttpStatus.OK);
  }
}
