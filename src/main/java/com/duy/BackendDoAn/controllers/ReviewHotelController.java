package com.duy.BackendDoAn.controllers;

import com.duy.BackendDoAn.dtos.ReviewDTO;
import com.duy.BackendDoAn.models.ReviewHotel;
import com.duy.BackendDoAn.responses.reviews.ReviewResponse;
import com.duy.BackendDoAn.services.ReviewHotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/reviewHotel")
@RestController
public class ReviewHotelController {
    private final ReviewHotelService reviewHotelService;

    @PostMapping()
    public ResponseEntity<ReviewResponse> postNewReview(@RequestBody ReviewDTO reviewDTO) throws Exception {
        ReviewResponse reviewResponse = new ReviewResponse();
        ReviewHotel reviewHotel = reviewHotelService.postNewReview(reviewDTO);
        reviewResponse = ReviewResponse.fromReview(reviewHotel);
        return ResponseEntity.ok(reviewResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteReview(@PathVariable long id, @RequestHeader("Authorization") String authorizationHeader) throws Exception {
        String extractedToken = authorizationHeader.substring(7);
        boolean result = reviewHotelService.deleteReview(id, extractedToken);
        if(result) return ResponseEntity.ok("Review deleted successfully!");
        return ResponseEntity.badRequest().body("Delete not done!");

    }

    @PutMapping("/{id}")
    public ResponseEntity<ReviewResponse> updateReview(@PathVariable long id, @RequestBody ReviewDTO reviewDTO) throws Exception {
        ReviewHotel newReview = reviewHotelService.updateReview(id, reviewDTO);
        return ResponseEntity.ok(ReviewResponse.fromReview(newReview));
    }
    
}
