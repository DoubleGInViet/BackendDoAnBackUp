package com.duy.BackendDoAn.responses;

import com.duy.BackendDoAn.models.Hotel;
import com.duy.BackendDoAn.models.HotelImage;
import com.duy.BackendDoAn.models.Room;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HotelResponse {
    private Long id;

    @JsonProperty("name")
    private String hotelName;

    @JsonProperty("type")
    private String typeOfHotel;

    private String description;

    @JsonProperty("address")
    private String address;

    @JsonProperty("email")
    private String hotelEmail;

    @JsonProperty("longitude")
    private Float longitude;

    @JsonProperty("latitude")
    private Float latitude;

    @JsonProperty("checkIn_time")
    private LocalTime checkInTime;

    @JsonProperty("checkOut_time")
    private LocalTime checkOutTime;

    @JsonProperty("phone_number")
    private String phoneNumber;

    private Float rating;

    @JsonProperty("city")
    private String city;

    @JsonProperty("images")
    private List<HotelImage> hotelImages = new ArrayList<>();

    @JsonProperty("rooms")
    private List<Room> rooms = new ArrayList<>();



    public static HotelResponse fromHotel(Hotel hotel){
        HotelResponse hotelResponse = HotelResponse.builder()
                .id(hotel.getId())
                .hotelName(hotel.getHotelName())
                .hotelEmail(hotel.getHotelEmail())
                .description(hotel.getDescription())
                .longitude(hotel.getLongitude())
                .latitude(hotel.getLatitude())
                .phoneNumber(hotel.getPhone_number())
                .address(hotel.getAddress())
                .city(hotel.getCity().getCity_name())
                .typeOfHotel(hotel.getType_of_hotel())
                .hotelImages(hotel.getHotelImages())
                .rooms(hotel.getRooms())
                .build();
        return hotelResponse;
    }
}
