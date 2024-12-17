package com.duy.BackendDoAn.controllers;


import com.duy.BackendDoAn.dtos.UpdateVehicleDTO;
import com.duy.BackendDoAn.dtos.VehicleDTO;
import com.duy.BackendDoAn.models.City;
import com.duy.BackendDoAn.models.Vehicle;
import com.duy.BackendDoAn.responses.vehicles.*;
import com.duy.BackendDoAn.services.CityService;
import com.duy.BackendDoAn.services.VehicleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RequestMapping("/vehicles")
@RestController
public class VehicleController {
    private final VehicleService vehicleService;
    private final CityService cityService;

    @GetMapping("")
    public ResponseEntity<VehicleListResponse> searchCar(
            @RequestParam(defaultValue = "") String location,
            @RequestParam Long id,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10000") int limit
    ) {
        if (limit >= 10000) {
            limit = Integer.MAX_VALUE;
        }
        
        PageRequest pageRequest = PageRequest.of(
                page, limit,
                Sort.by("id").ascending()
        );

//        DateTimeFormatter dateFormat = (DateTimeFormatter.ISO_LOCAL_DATE);
//        LocalDate start_date = LocalDate.parse(startDate, dateFormat);
//        LocalDate end_date = LocalDate.parse(endDate, dateFormat);
//
//        DateTimeFormatter timeFormatter = (DateTimeFormatter.ofPattern("HH:mm"));
//        LocalTime start_time = LocalTime.parse(startTime, timeFormatter);
//        LocalTime end_time = LocalTime.parse(endTime, timeFormatter);

        Page<VehicleResponse> carPage = vehicleService.getAllVehicle(location, id, pageRequest);
        int totalPages = carPage.getTotalPages();
        List<VehicleResponse> vehicles = carPage.getContent();
        List<VehicleResponse> filteredVehicles = vehicles.stream()
                .filter(vehicle -> vehicle.getRentalResponses() != null && !vehicle.getRentalResponses().isEmpty())
                .collect(Collectors.toList());
        return ResponseEntity.ok(VehicleListResponse.builder()
                .vehicles(filteredVehicles)
                .totalPages(totalPages)
                .build());
    }


    @PostMapping()
    public ResponseEntity<VehicleResponse> createNewVehicle(@Valid @RequestBody VehicleDTO vehicleDTO) throws Exception {
        Vehicle savedVehicle = new Vehicle();
        if(vehicleDTO.getType().equals("motor")) {
            savedVehicle = vehicleService.addMotor(vehicleDTO);
        } else if (vehicleDTO.getType().equals("car")) {
            savedVehicle = vehicleService.addCar(vehicleDTO);
        }
        else {
            throw new Exception("Invalid vehicle type");
        }
        VehicleResponse response = VehicleResponse.fromVehicle(savedVehicle);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VehicleResponse> getVehicleById(@PathVariable("id") Long id) throws Exception {
        return ResponseEntity.ok(vehicleService.getSpecificVehicleById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<VehicleResponse> updateVehicle(@PathVariable("id") Long id, @Valid @RequestBody UpdateVehicleDTO vehicleDTO) throws Exception {
        Vehicle vehicle = new Vehicle();
        if(vehicleDTO.getType().equals("car")){
            vehicle = vehicleService.updateCar(id, vehicleDTO);
        }
        else if (vehicleDTO.getType().equals("motor")) {
            vehicle = vehicleService.updateMotor(id, vehicleDTO);
        }
        else {
            throw new Exception("Invalid vehicle type");
        }
        VehicleResponse vehicleResponse = VehicleResponse.fromVehicle(vehicle);
        return ResponseEntity.ok(vehicleResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteVehicle(@PathVariable Long id) throws Exception {
        try {
            vehicleService.deleteVehicleById(id);
            return ResponseEntity.ok("Vehicle delete successfully");
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }
}
