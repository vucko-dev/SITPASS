package com.example.sitpass.service.impl;

import com.example.sitpass.dto.CommentDTO;
import com.example.sitpass.dto.ReviewDTO;
import com.example.sitpass.model.Comment;
import com.example.sitpass.model.Rate;
import com.example.sitpass.model.Review;
import com.example.sitpass.model.User;
import com.example.sitpass.repository.ReviewRepository;
import com.example.sitpass.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {


  @Autowired
  private ReviewRepository reviewRepository;

  @Autowired
  private CommentService commentService;

  @Autowired
  private FacilityService facilityService;

  @Autowired
  private UserService userService;

  @Autowired
  private ExerciseService exerciseService;

  @Autowired
  private RateService rateService;

  @Autowired
  private ManagesService managesService;


  @Override
  public Review getReviewById(Long id) {
    return reviewRepository.findById(id).orElse(null);
  }

  @Override
  public Review save(ReviewDTO reviewDTO, Long userId) {

    if(reviewRepository.findByFacilityIdAndUserId((Long) reviewDTO.getFacilityId(), userId) != null) {
      throw new RuntimeException("Vec ste ostavili utisak na ovu teretanu!");
    }

    Integer numberOfExercises = exerciseService.getExercisesCountByFacilityId(reviewDTO.getFacilityId(),userId);
    if(numberOfExercises == 0){
      throw new RuntimeException("Niste posetili ovu teretanu!");
    }
    Review review = new Review();
//    review.setId(reviewDTO.getId());
    review.setFacility(facilityService.getFacilityById(reviewDTO.getFacilityId()));
    review.setCreatedAt(LocalDateTime.now());
    review.setUser(userService.findById(userId));
    review.setExerciseCount(exerciseService.getExercisesCountByFacilityId(reviewDTO.getFacilityId(), userId));
    review.setHidden(false);
    Rate rate = rateService.save(reviewDTO.getRate());
    review.setRate(rate);
    Review returnRev = reviewRepository.save(review);
    if(reviewDTO.getCommentDTO()!=null) { // Ovo treba doriaditi returnRev.getId();
      Comment comment = this.commentService.addComment(reviewDTO.getCommentDTO(), returnRev.getId(), userId);
      review.setComment(comment);
    }
    return returnRev;
  }

  @Override
  public List<Review> getReviewsByUserId(Long userId) {
    return reviewRepository.findByUserId(userId);
  }

  @Override
  public List<Review> getReviewsByFacilityId(Long facilityId) {
    return reviewRepository.findByFacilityId(facilityId);
  }

  @Override
  public void showReviewById(Long id){
    User user = userService.getCurrentUser();
    String role = user.getRoles().get(0).getName();
    Review review = reviewRepository.findById(id).orElse(null);

    if(review == null){
      throw new RuntimeException("Review not found!");
    }

    if(!(role.equals("ADMIN")||(role.equals("MANAGER") && managesService.hasRightsToFacility(user.getId(),review.getFacility().getId())))){
      throw new RuntimeException("Insufficient permission!");
    }
    review.setHidden(false);

  }

  @Override
  public void hideReviewById(Long id){
    User user = userService.getCurrentUser();
    String role = user.getRoles().get(0).getName();
    Review review = reviewRepository.findById(id).orElse(null);

    if(review == null){
      throw new RuntimeException("Review not found!");
    }

    if(!(role.equals("ADMIN")||(role.equals("MANAGER") && managesService.hasRightsToFacility(user.getId(),review.getFacility().getId())))){
      throw new RuntimeException("Insufficient permission!");
    }
    review.setHidden(true);
  }


  @Override
  public void deleteReviewById(Long id){
    Review review = reviewRepository.findById(id).orElse(null);
    if(review == null){
      throw new RuntimeException("Review not found!");
    }
    Comment comment = review.getComment();
    if(comment != null){
      commentService.deleteCommentById(comment.getId());
    }
//
//    Rate rate = review.getRate();
//    if(rate != null){
//      rateService.deleteFacilityRating(rate.getId());
//    }
    reviewRepository.delete(review);
  }

}
