package com.duy.BackendDoAn.services;

import com.duy.BackendDoAn.models.DailyTicketAvailability;
import com.duy.BackendDoAn.models.TicketClass;
import com.duy.BackendDoAn.models.Tour;
import com.duy.BackendDoAn.models.TourSchedule;
import com.duy.BackendDoAn.repositories.DailyTicketAvailabilityRepository;
import com.duy.BackendDoAn.repositories.TourRepository;
import com.duy.BackendDoAn.repositories.TourScheduleRepository;
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
    @Scheduled(cron = "0 58 23 * * *")
    public void generateTourSchedules() {
        LocalDate today = LocalDate.now();
        LocalDate endDate = today.plusDays(11);

        List<Tour> allTours = tourRepository.findAll();
        for(Tour tour : allTours) {
            List<TourSchedule> tourSchedules = tourScheduleRepository.findByTourIdAndHappenDate(tour.getId(), today);
            for(TourSchedule tourSchedule: tourSchedules) {
                for (LocalDate date = today; !date.isAfter(endDate); date = date.plusDays(1)) {
                    boolean exists = tourScheduleRepository.existsByTourIdAndStartTimeAndEndTimeAndHappenDate(
                            tour.getId(),
                            tourSchedule.getStartTime(),
                            tourSchedule.getEndTime(),
                            date
                    );
                    if(!exists) {
                        TourSchedule newTourSchedule = new TourSchedule();
                        newTourSchedule.setTour(tour);
                        newTourSchedule.setHappenDate(date);
                        newTourSchedule.setStartTime(tourSchedule.getStartTime());
                        newTourSchedule.setEndTime(tourSchedule.getEndTime());

                        tourScheduleRepository.save(newTourSchedule);
                        List<TicketClass> ticketClasses = tour.getTicketClasses();
                        for(TicketClass ticketClass : ticketClasses) {
                            DailyTicketAvailability availability = new DailyTicketAvailability();
                            availability.setTourSchedule(tourSchedule);
                            availability.setTicketClass(ticketClass);
                            availability.setAvailableTicket(ticketClass.getMaxAmount());
                            dailyTicketAvailabilityRepository.save(availability);
                        }
                    }
                }
            }


        }

    }
}
