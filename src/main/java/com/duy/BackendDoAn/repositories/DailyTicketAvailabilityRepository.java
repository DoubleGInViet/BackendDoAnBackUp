package com.duy.BackendDoAn.repositories;

import com.duy.BackendDoAn.models.DailyTicketAvailability;
import com.duy.BackendDoAn.models.TicketClass;
import com.duy.BackendDoAn.models.TourSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface DailyTicketAvailabilityRepository extends JpaRepository<DailyTicketAvailability, Long> {
    Optional<DailyTicketAvailability> findByTourScheduleAndTicketClass(TourSchedule tourSchedule, TicketClass ticketClass);
    boolean existsByTourScheduleAndHappenDateAndTicketClass(TourSchedule tourSchedule, LocalDate date, TicketClass ticketClass);
}
