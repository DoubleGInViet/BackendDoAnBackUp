package com.duy.BackendDoAn.responses.bookingVehicles;

import com.duy.BackendDoAn.models.AdditionDriver;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DriverResponse {
    private String title;
    private String fullName;
    private String phone;

    public static DriverResponse fromDriver(AdditionDriver additionDriver) {
        DriverResponse response = new DriverResponse();
        if(additionDriver.getTitle() == 0) response.setTitle("Bà");
        else response.setTitle("Ông");
        response.setFullName(additionDriver.getFullName());
        response.setPhone(additionDriver.getPhone());
        return response;
    }
}
