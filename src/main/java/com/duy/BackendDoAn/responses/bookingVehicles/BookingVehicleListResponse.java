package com.duy.BackendDoAn.responses.bookingVehicles;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookingVehicleListResponse {
    private List<BookingVehicleResponse> response;
    private int totalPages;
}
