package com.duy.BackendDoAn.repositories;

import com.duy.BackendDoAn.models.DailyTicketAvailability;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DailyTicketAvailabilityRepository extends JpaRepository<DailyTicketAvailability, Long> {
}
