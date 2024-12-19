package com.duy.BackendDoAn.dtos.bookingVehicleDTOs;

import com.duy.BackendDoAn.dtos.AccessoryBookingDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BookingVehicleDTO {
    @JsonProperty("user")
    private Long user;

    @JsonProperty("facility")
    private Long rental;

    @JsonProperty("vehicle")
    private Long vehicle;

    @JsonProperty("pickup")
    private PlaceAndDateDTO pickup;

    @JsonProperty("return")
    private PlaceAndDateDTO return_place;

    @JsonProperty("total_price")
    private Long totalPrice;

    @JsonProperty("status")
    private String status;

    @JsonProperty("customerInfo")
    private CustomerInfoDTO customerInfoDTO;

    @JsonProperty("accessory_booking")
    private List<AccessoryBookingDTO> accessoryBookings;

    @JsonProperty("driverInfo")
    private List<DriverInfoDTO> driverInfoDTOS;

}
