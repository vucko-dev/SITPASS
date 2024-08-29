package com.example.sitpass.controller;


import com.example.sitpass.dto.ReviewDTO;
import com.example.sitpass.model.Review;
import com.example.sitpass.model.User;
import com.example.sitpass.service.RateService;
import com.example.sitpass.service.ReviewService;
import com.example.sitpass.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/review")
public class ReviewController {

  @Autowired
  private ReviewService reviewService;

  @Autowired
  private UserService userService;

  @Autowired
  private RateService rateService;

  @GetMapping
  @PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'MANAGER')")
  public ResponseEntity<List<Review>> getReviewsFromUserId(Principal principal) {
    User user = userService.findByUsername(principal.getName());
    List<Review> reviews = reviewService.getReviewsByUserId(user.getId());
    return new ResponseEntity<>(reviews, HttpStatus.OK);
  }

  @GetMapping("/facility/{id}")
  @PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'MANAGER')")
  public ResponseEntity<List<Review>> getReviewsByFacilityId(@PathVariable("id") Long id) {
    List<Review> reviews = reviewService.getReviewsByFacilityId(id);
    return new ResponseEntity<>(reviews, HttpStatus.OK);
  }

  @GetMapping("{id}")
  @PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'MANAGER')")
  public ResponseEntity<Review> getReviewById(@PathVariable("id") Long id) {
    Review review = reviewService.getReviewById(id);
    return new ResponseEntity<>(review, HttpStatus.OK);
  }


  @PostMapping
  @PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'MANAGER')")
  public ResponseEntity<Review> addReview(@RequestBody ReviewDTO reviewDTO) {
    Review review = reviewService.save(reviewDTO);
    rateService.updateFacilityRating(reviewDTO.getFacilityId());
    return new ResponseEntity<>(review, HttpStatus.CREATED);
  }

  @PutMapping("/show/{id}")
  @PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'MANAGER')")
  public void showReview(@PathVariable("id") Long id){
    Review review = reviewService.getReviewById(id);
    if(review != null){
      reviewService.showReviewById(id);
    } else{
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
  }

  @PutMapping("/hide/{id}")
  @PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'MANAGER')")
  public void hideReview(@PathVariable("id") Long id){
    Review review = reviewService.getReviewById(id);
    if(review != null){
      reviewService.hideReviewById(id);
    } else{
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
  }



}
