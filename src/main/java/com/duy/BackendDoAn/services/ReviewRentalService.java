package com.duy.BackendDoAn.services;

import com.duy.BackendDoAn.dtos.ReviewDTO;
import com.duy.BackendDoAn.models.RentalFacility;
import com.duy.BackendDoAn.models.ReviewRentalFacility;
import com.duy.BackendDoAn.models.User;
import com.duy.BackendDoAn.repositories.RentalFacilityRepository;
import com.duy.BackendDoAn.repositories.ReviewRentalRepository;
import com.duy.BackendDoAn.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ReviewRentalService {
    private final RentalFacilityRepository rentalFacilityRepository;
    private final UserRepository userRepository;
    private final ReviewRentalRepository reviewRentalRepository;
    private final RentalFacilityService rentalFacilityService;
    public ReviewRentalFacility postNewReview(ReviewDTO reviewDTO) {
        RentalFacility rentalFacility = rentalFacilityRepository.findById(reviewDTO.getProduct()).orElseThrow(()-> new RuntimeException("Rental facility not exist!"));
        User user = userRepository.findById(reviewDTO.getUser()).orElseThrow(()->new RuntimeException("User not found!"));
        boolean existed = reviewRentalRepository.existsByUserIdAndRentalFacilityId(user.getId(), rentalFacility.getId());
        if (existed) throw new IllegalStateException("User already review this rental");
        ReviewRentalFacility reviewRentalFacility = ReviewRentalFacility.builder()
                .rating(reviewDTO.getRating())
                .comment(reviewDTO.getComment())
                .review_date(LocalDateTime.now())
                .rentalFacility(rentalFacility)
                .user(user)
                .build();
//        rentalFacilityService.updateRatingOnAddNewReview(reviewDTO.getProduct(), reviewRentalFacility.getRating());
        return reviewRentalRepository.save(reviewRentalFacility);
    }
}
