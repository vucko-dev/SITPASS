package com.example.sitpass.controller;


import com.example.sitpass.dto.CommentDTO;
import com.example.sitpass.model.Comment;
import com.example.sitpass.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {

  @Autowired
  private CommentService commentService;

  @PostMapping
  @PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'MANAGER')")
  public ResponseEntity<Comment> addComment(@RequestBody CommentDTO commentDTO) {
    Comment comment = commentService.addComment(commentDTO);
    return new ResponseEntity<>(comment, HttpStatus.CREATED);
  }

  @GetMapping("/review/{id}")
  @PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'MANAGER')")
  public ResponseEntity<List<Comment>> getCommentsByReviewId(@PathVariable Long id) {
    List<Comment> comments = commentService.getCommentsByReviewId(id);
    return new ResponseEntity<>(comments, HttpStatus.OK);
  }

  @GetMapping("{id}")
  @PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'MANAGER')")
  public ResponseEntity<Comment> getCommentById(@PathVariable Long id) {
    Comment comment = commentService.getCommentById(id);
    return new ResponseEntity<>(comment, HttpStatus.OK);
  }
}
