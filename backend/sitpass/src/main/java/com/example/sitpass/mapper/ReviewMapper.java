package com.example.sitpass.mapper;

import com.example.sitpass.dto.ReviewDTO;
import com.example.sitpass.model.Review;
import com.example.sitpass.service.CommentService;
import com.example.sitpass.service.FacilityService;
import com.example.sitpass.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class ReviewMapper implements MapperInterface<Review, ReviewDTO> {


  @Autowired
  private UserService userService;

  @Autowired
  private FacilityService facilityService;

  @Autowired
  private CommentService commentService;

  @Autowired
  private CommentMapper commentMapper;

  @Autowired
  private RateMapper rateMapper;

  public Review toEntity(ReviewDTO dto) {
    Review review = new Review();
    review.setId(dto.getId());
    review.setHidden(dto.getHidden());
    review.setUser(userService.findById(dto.getUserId()));
    review.setFacility(facilityService.getFacilityById(dto.getFacilityId()));
    review.setCreatedAt(dto.getCreatedAt());
    if(dto.getCommentDTO()!=null){
      review.setComment(commentService.getCommentById(dto.getId()));
    }
    review.setExerciseCount(dto.getExerciseCount());
    return review;
  }

  public ReviewDTO toDto(Review review) {
    ReviewDTO dto = new ReviewDTO();
    dto.setId(review.getId());
    dto.setHidden(review.getHidden());
    dto.setUserId(review.getUser().getId());
    dto.setFacilityId(review.getFacility().getId());
    dto.setCreatedAt(review.getCreatedAt());
    dto.setExerciseCount(review.getExerciseCount());
    if(review.getComment() != null){
      dto.setCommentDTO(commentMapper.toDto(review.getComment()));
    }
    dto.setRate(rateMapper.toDto(review.getRate()));
    return dto;
  }
}
