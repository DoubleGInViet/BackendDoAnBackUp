package com.duy.BackendDoAn.services;

import com.duy.BackendDoAn.dtos.ReviewDTO;
import com.duy.BackendDoAn.models.Hotel;
import com.duy.BackendDoAn.models.ReviewHotel;
import com.duy.BackendDoAn.models.User;
import com.duy.BackendDoAn.repositories.HotelRepository;
import com.duy.BackendDoAn.repositories.ReviewHotelRepository;
import com.duy.BackendDoAn.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.security.access.AccessDeniedException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewHotelService {
    private final ReviewHotelRepository reviewHotelRepository;
    private final HotelRepository hotelRepository;
    private final UserRepository userRepository;
    private final HotelService hotelService;
    private final UserService userService;

    public ReviewHotel postNewReview(ReviewDTO reviewDTO) throws Exception {
        Hotel hotel = hotelRepository.findById(reviewDTO.getProduct()).orElseThrow(()-> new Exception("Hotel not found"));
        User user = userRepository.findById(reviewDTO.getUser()).orElseThrow(()-> new Exception("User not found"));
        boolean existed = reviewHotelRepository.existsByUserIdAndHotelId(reviewDTO.getUser(), reviewDTO.getProduct());
        if(existed) throw new IllegalStateException("User already rate this hotel");
        ReviewHotel reviewHotel = ReviewHotel.builder()
                .rating(reviewDTO.getRating())
                .comment(reviewDTO.getComment())
                .review_date(LocalDateTime.now())
                .hotel(hotel)
                .user(user)
                .build();
        hotelService.updateRatingOnAddNewReview(reviewDTO.getProduct(), reviewDTO.getRating());
        return reviewHotelRepository.save(reviewHotel);
    }

    public boolean deleteReview(long id, String extractedToken) throws Exception {
        ReviewHotel reviewHotel = reviewHotelRepository.findById(id).orElseThrow(()-> new Exception("Review doesnt exist!"));

        User nowUser = userService.getUserDetailFromToken(extractedToken);
        if(reviewHotel.getUser() != nowUser){
            return false;
        }
        hotelService.updateRatingOnDeleteReview(reviewHotel.getHotel().getId(), reviewHotel.getRating());
        reviewHotelRepository.delete(reviewHotel);
        return true;
    }

    public ReviewHotel updateReview(Long id, ReviewDTO reviewDTO) throws Exception {
        ReviewHotel existingReview = reviewHotelRepository.findById(id).orElseThrow(()-> new Exception("Review hotel not exist!!"));
        User nowUser = userRepository.findById(reviewDTO.getUser()).orElseThrow(()-> new Exception("User not found!!"));
        if(existingReview.getUser() != nowUser){
            throw new AccessDeniedException("You can't change other's review");
        }
        hotelService.updateRatingOnUpdateReview(existingReview.getHotel(), reviewDTO.getRating(), existingReview.getRating());

        existingReview.setComment(reviewDTO.getComment());
        existingReview.setRating(reviewDTO.getRating());
        return reviewHotelRepository.save(existingReview);
    }

    
}
