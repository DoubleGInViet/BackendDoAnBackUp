package com.duy.BackendDoAn.responses.tours;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TourListResponse {
    private List<TourResponse> responses;
    private int totalPages;
}
