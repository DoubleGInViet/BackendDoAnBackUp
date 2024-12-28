package com.duy.BackendDoAn.responses.vehicles;

import com.duy.BackendDoAn.models.*;
import com.duy.BackendDoAn.responses.AttractionResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.duy.BackendDoAn.responses.rentalFacilities.RentalResponse;
import lombok.*;

import java.text.CharacterIterator;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VehicleResponse {
    private Long id;

    private String model;

    @JsonProperty("facilities")
    private List<RentalResponse> rentalResponses;

    private String type;

    private DetailResponse details;

    @JsonProperty("image_url")
    private String imageUrl;


    public static VehicleResponse fromVehicle(Vehicle vehicle) {
        VehicleResponse response = new VehicleResponse();
        response.id = vehicle.getId();
        response.model = vehicle.getName();
        response.imageUrl = vehicle.getImageUrl();
        if (vehicle.getVehicleRentalFacilities() != null) {
            // Chuyển đổi danh sách vehicleRentalFacilities thành danh sách ID hoặc tên
            response.setRentalResponses(
                    vehicle.getVehicleRentalFacilities().stream()
                            .map(RentalResponse::fromRentalFacility) // Hoặc sử dụng ID, tùy thuộc vào yêu cầu của bạn
                            .collect(Collectors.toList())
            );
        } else {
            response.setRentalResponses(Collections.emptyList()); // Trả về danh sách rỗng nếu null
        }
        response.type = vehicle.getVehicle_type();
        if ("car".equalsIgnoreCase(vehicle.getVehicle_type())) {
            response.details = DetailCarResponse.fromCar((Car) vehicle);
        } else if ("motor".equalsIgnoreCase(vehicle.getVehicle_type())) {
            response.details = DetailMotorResponse.fromMotor((Motor) vehicle);
        } else {
            response.details = null; // Hoặc xử lý ngoại lệ nếu cần
        }
        return response;
    }

    public static VehicleResponse fromVehicleWithCity(Vehicle vehicle, City city) {
        VehicleResponse response = new VehicleResponse();
        response.id = vehicle.getId();
        response.model = vehicle.getName();
        response.type = vehicle.getVehicle_type();
        response.imageUrl = vehicle.getImageUrl();

        if(vehicle.getVehicleRentalFacilities() != null){
            List<VehicleRentalFacility> relevantFacilities = vehicle.getVehicleRentalFacilities().stream()
                    .filter(vrf -> hasOfficeInCity(vrf.getRentalFacility(), city)) // Kiểm tra điều kiện
                    .collect(Collectors.toList());
            response.setRentalResponses(
                    relevantFacilities.stream()
                            .map(RentalResponse::fromRentalFacility)
                            .collect(Collectors.toList())
            );
        }
        else {
            response.setRentalResponses(Collections.emptyList());
        }
        if ("car".equalsIgnoreCase(vehicle.getVehicle_type())) {
            response.details = DetailCarResponse.fromCar((Car) vehicle);
        } else if ("motor".equalsIgnoreCase(vehicle.getVehicle_type())) {
            response.details = DetailMotorResponse.fromMotor((Motor) vehicle);
        } else {
            response.details = null; // Hoặc xử lý ngoại lệ nếu cần
        }
        return response;
    }

    private static boolean hasOfficeInCity(RentalFacility rentalFacility, City city) {
        return rentalFacility.getOffices().stream()
                .anyMatch(office -> office.getAttraction() != null &&
                        city.equals(office.getAttraction().getCity()));
    }
}
