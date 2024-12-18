package com.duy.BackendDoAn.controllers;

import com.duy.BackendDoAn.models.RentalFacility;
import com.duy.BackendDoAn.responses.allRentals.RentalFacilitiesResponse;
import com.duy.BackendDoAn.responses.rentalFacilities.RentalResponse;
import com.duy.BackendDoAn.services.RentalFacilityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RequestMapping("/rental")
@RestController
public class RentalFacilityController {
    private final RentalFacilityService rentalFacilityService;
    @GetMapping()
    public ResponseEntity<List<RentalFacilitiesResponse>> getAll() {
        List<RentalFacility> list = rentalFacilityService.getAll();
        return ResponseEntity.ok(list.stream()
                .map(RentalFacilitiesResponse::fromRental)
                .collect(Collectors.toList()));
    }
}
