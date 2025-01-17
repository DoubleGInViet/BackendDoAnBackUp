package com.duy.BackendDoAn.repositories;

import com.duy.BackendDoAn.models.Tour;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface TourRepository extends JpaRepository<Tour, Long> {
    @Query("""
    SELECT DISTINCT t 
    FROM Tour t
    JOIN t.attraction a
    JOIN a.city c
    JOIN t.tourSchedules ts
    JOIN ts.dailyTicketAvailabilities dta
    WHERE (:location IS NULL OR REPLACE(c.city_name, ' ', '') LIKE %:location)
      AND t.active = true
      AND dta.availableTicket > 0
      AND (:date IS NULL OR dta.happenDate = :date)
""")
    Page<Tour> searchTours(
            @Param("location") String location,
            @Param("date") LocalDate date,
            Pageable pageable
            );

    @Query("SELECT t FROM Tour t WHERE t.active = true")
    List<Tour> findAllActiveHotels();

}
