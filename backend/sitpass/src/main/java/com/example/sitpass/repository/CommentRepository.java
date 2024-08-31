package com.example.sitpass.repository;

import com.example.sitpass.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
  Optional<Comment> findById(Long id);
  List<Comment> findByReviewId(Long reviewId);
  List<Comment> findByUserIdAndReviewId(Long userId, Long reviewId);
}
