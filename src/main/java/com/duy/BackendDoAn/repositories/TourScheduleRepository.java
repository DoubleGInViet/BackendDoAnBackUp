package com.duy.BackendDoAn.repositories;

import com.duy.BackendDoAn.models.TourSchedule;
import com.duy.BackendDoAn.responses.tours.TourResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface TourScheduleRepository extends JpaRepository<TourSchedule, Long> {



}
