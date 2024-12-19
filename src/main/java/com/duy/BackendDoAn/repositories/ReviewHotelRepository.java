package com.duy.BackendDoAn.repositories;

import com.duy.BackendDoAn.models.Hotel;
import com.duy.BackendDoAn.models.ReviewHotel;
import com.duy.BackendDoAn.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReviewHotelRepository extends JpaRepository<ReviewHotel, Long> {
    boolean existsByUserIdAndHotelId(Long userId, Long hotelId);
    Optional<ReviewHotel> findByUserAndHotel(User user, Hotel hotel);
}
