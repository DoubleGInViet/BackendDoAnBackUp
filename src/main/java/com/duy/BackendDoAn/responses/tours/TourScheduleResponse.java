package com.duy.BackendDoAn.responses.tours;

import com.duy.BackendDoAn.models.TourSchedule;
import lombok.*;

import java.time.DayOfWeek;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TourScheduleResponse {
    private String date;
    private Long count;
    private List<DailyTicketAvailabilityResponse> dailyTicketAvailabilities;

    public static TourScheduleResponse fromTourSchedule(TourSchedule tourSchedule) {
        TourScheduleResponse response = new TourScheduleResponse();

        response.date = tourSchedule.getHappenDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        response.dailyTicketAvailabilities = tourSchedule.getDailyTicketAvailabilities().stream()
                .map(DailyTicketAvailabilityResponse::fromDailyTicket)
                .collect(Collectors.toList());
        response.count = response.dailyTicketAvailabilities.stream()
                .mapToLong(DailyTicketAvailabilityResponse::getAvailableTicket)
                .sum();
        return response;
    }
}
