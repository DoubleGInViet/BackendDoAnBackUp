package com.duy.BackendDoAn.services;

import com.duy.BackendDoAn.models.DailyTicketAvailability;
import com.duy.BackendDoAn.models.TicketClass;
import com.duy.BackendDoAn.models.Tour;
import com.duy.BackendDoAn.models.TourSchedule;
import com.duy.BackendDoAn.repositories.DailyTicketAvailabilityRepository;
import com.duy.BackendDoAn.repositories.TourRepository;
import com.duy.BackendDoAn.repositories.TourScheduleRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TourScheduleService {
    private final TourRepository tourRepository;
    private final TourScheduleRepository tourScheduleRepository;
    private final DailyTicketAvailabilityRepository dailyTicketAvailabilityRepository;

    @Transactional
    @Scheduled(cron = "0 * * * * *")
    public void generateTourSchedules() {
        LocalDate today = LocalDate.now();
        LocalDate endDate = today.plusDays(11);

        List<Tour> allTours = tourRepository.findAll();
        for(Tour tour : allTours) {
            for(TourSchedule tourSchedule: tour.getTourSchedules()) {
                for (LocalDate date = today; !date.isAfter(endDate); date = date.plusDays(1)) {
                    for (TicketClass ticketClass : tour.getTicketClasses()){
                        boolean exists = dailyTicketAvailabilityRepository
                                .existsByTourScheduleAndHappenDateAndTicketClass(tourSchedule, date, ticketClass);
                        if (!exists) {
                            DailyTicketAvailability availability = DailyTicketAvailability.builder()
                                    .happenDate(date)
                                    .tourSchedule(tourSchedule)
                                    .ticketClass(ticketClass)
                                    .availableTicket(ticketClass.getMaxAmount())
                                    .build();
                            dailyTicketAvailabilityRepository.save(availability);
                        }
                    }

                }
            }


        }

    }
}
