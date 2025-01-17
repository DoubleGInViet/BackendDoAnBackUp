package com.duy.BackendDoAn.responses.tours;

import com.duy.BackendDoAn.models.TourSchedule;
import com.fasterxml.jackson.annotation.JsonProperty;
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
    private Long id;

    @JsonProperty("start_time")
    private String startTime;

    @JsonProperty("end_time")
    private String endTime;

    private Long count;
    private List<DailyTicketAvailabilityResponse> dailyTicketAvailabilities;

    public static TourScheduleResponse fromTourSchedule(TourSchedule tourSchedule) {
        TourScheduleResponse response = new TourScheduleResponse();
        response.id = tourSchedule.getId();
        response.startTime = tourSchedule.getStartTime().format(DateTimeFormatter.ofPattern("hh:mm"));
        response.endTime = tourSchedule.getEndTime().format(DateTimeFormatter.ofPattern("hh:mm"));
        response.dailyTicketAvailabilities = tourSchedule.getDailyTicketAvailabilities().stream()
                .map(DailyTicketAvailabilityResponse::fromDailyTicket)
                .collect(Collectors.toList());
        response.count = response.dailyTicketAvailabilities.stream()
                .mapToLong(DailyTicketAvailabilityResponse::getAvailableTicket)
                .sum();
        return response;
    }
}
