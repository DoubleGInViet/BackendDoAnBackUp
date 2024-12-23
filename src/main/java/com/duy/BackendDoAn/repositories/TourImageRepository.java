package com.duy.BackendDoAn.repositories;

import com.duy.BackendDoAn.models.TourImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TourImageRepository extends JpaRepository<TourImage, Long> {
    int countByTourId(Long tourId);
}
