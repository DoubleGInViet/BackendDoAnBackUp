package com.duy.BackendDoAn.responses.hotels;

import com.duy.BackendDoAn.models.Hotel;
import com.duy.BackendDoAn.models.HotelImage;
import com.duy.BackendDoAn.models.Room;
import com.duy.BackendDoAn.responses.RoomResponse;
import com.duy.BackendDoAn.responses.cities.CityResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HotelResponse {
    private Long id;

    @JsonProperty("hotel_name")
    private String hotelName;

    @JsonProperty("type")
    private String typeOfHotel;

    private String description;

    @JsonProperty("address")
    private String address;

    @JsonProperty("city")
    private CityResponse city;

    @JsonProperty("location")
    private LocationResponse locationResponse;

    @JsonProperty("amenities")
    private List<String> amenities;

    @JsonProperty("checkIn_time")
    private String checkInTime;

    @JsonProperty("checkOut_time")
    private String checkOutTime;

    @JsonProperty("hotel_policies")
    private HotelPolicyResponse hotelPolicyResponse;

    @JsonProperty("phone_number")
    private String phoneNumber;

    @JsonProperty("email")
    private String email;

    @JsonProperty("website")
    private String website;

    @JsonProperty("images")
    private List<String> images;

    @JsonProperty("rooms")
    private List<RoomResponse> rooms;

    @JsonProperty("reviews")
    private ReviewResponse reviews;

    public static HotelResponse fromHotel(Hotel hotel) {
        HotelResponse response = new HotelResponse();

        response.id = hotel.getId();
        response.hotelName = hotel.getHotelName();
        response.typeOfHotel = hotel.getType_of_hotel();
        response.description = hotel.getDescription();
        response.address = hotel.getAddress();
        if (hotel.getCity() != null) {
            response.city = CityResponse.fromCity(hotel.getCity());
        }

        // Check if nearbyAttractions is null
        response.locationResponse = (hotel.getNearbyAttractions() != null)
                ? LocationResponse.fromLocation(hotel)
                : new LocationResponse(); // or a default value

        // Check if amenities list is null
        response.amenities = (hotel.getAmenityForHotels() != null)
                ? hotel.getAmenityForHotels().stream()
                .map(amenityForHotel -> amenityForHotel.getAmenity().getName())
                .collect(Collectors.toList())
                : new ArrayList<>(); // default empty list

        response.checkInTime = hotel.getCheck_in_time().format(DateTimeFormatter.ofPattern("HH:mm"));
        response.checkOutTime = hotel.getCheck_out_time().format(DateTimeFormatter.ofPattern("HH:mm"));

        response.hotelPolicyResponse = HotelPolicyResponse.fromHotelPolicy(hotel);


        response.phoneNumber = hotel.getPhone_number();
        response.email = hotel.getHotelEmail();
        response.website = hotel.getWebsite();

        // Check if hotelImages list is null
        response.images = (hotel.getHotelImages() != null)
                ? hotel.getHotelImages().stream()
                .map(HotelImage::getImage_url)
                .collect(Collectors.toList())
                : new ArrayList<>(); // default empty list

        // Check if rooms list is null
        response.rooms = (hotel.getRooms() != null)
                ? hotel.getRooms().stream()
                .filter(Room::isActive)
                .map(RoomResponse::fromRoom)
                .collect(Collectors.toList())
                : new ArrayList<>(); // default empty list

        // Check if reviews list is null
        response.reviews = (hotel.getReviews() != null)
                ? ReviewResponse.fromReviews(hotel)
                : new ReviewResponse(); // or a default value

        return response;
    }


}
