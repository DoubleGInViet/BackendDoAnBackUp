package com.duy.BackendDoAn.services;

import com.duy.BackendDoAn.models.RentalFacility;
import com.duy.BackendDoAn.repositories.RentalFacilityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RentalFacilityService {
    private final RentalFacilityRepository rentalFacilityRepository;
//    public void updateRatingOnAddNewReview(Long id, long newRating){
//        RentalFacility rental = rentalFacilityRepository.findById(id).orElseThrow(()-> new RuntimeException("Rental facility not exists"));
//        rental.setTotal_rating(rental.getTotal_rating() + newRating);
//        rental.setReview_count(rental.getReview_count()+1);
//        float average = (float) rental.getTotal_rating() / rental.getReview_count();
//        rental.setRating(Math.round(average * 10.0f) / 10.0f);
//        rentalFacilityRepository.save(rental);
//    }

    public List<RentalFacility> getAll() {
        return rentalFacilityRepository.findAll();
    }
}
