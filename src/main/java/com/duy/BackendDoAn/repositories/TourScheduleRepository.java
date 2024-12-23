package com.duy.BackendDoAn.repositories;

import com.duy.BackendDoAn.models.TourSchedule;
import com.duy.BackendDoAn.responses.tours.TourResponse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface TourScheduleRepository extends JpaRepository<TourSchedule, Long> {
    List<TourSchedule> findByTourIdAndHappenDate(Long tourId, LocalDate happenDate);
    boolean existsByTourIdAndStartTimeAndEndTimeAndHappenDate(Long tourId, LocalTime startTime, LocalTime endTime, LocalDate happenDate);
}
