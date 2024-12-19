package com.duy.BackendDoAn.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "hotel")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "hotel_name", length = 100)
    private String hotelName;

    @Column(name = "hotel_email", length = 100)
    private String hotelEmail;

    @Column(name = "phone_number")
    private String phone_number;

    @Column(name = "address")
    private String address;

    @Column(name = "longitude")
    private Float longitude;

    @Column(name = "latitude")
    private Float latitude;

    @Column(name = "description")
    private String description;

    @Column(name = "check_in_time")
    private LocalTime check_in_time;

    @Column(name = "check_out_time")
    private LocalTime check_out_time;

    @Column(name = "website")
    private String website;

    @Column(name = "review_count")
    private Long reviewCount;

    @Column(name = "cancellation_policy")
    private String cancellation_policy;

    @Column(name = "deposit_required")
    private String deposit_required;

    @Column(name = "pets_allowed")
    private boolean pets_allowed;

    @Column(name = "smoking_policy")
    private boolean smoking_policy;

    @Column(name = "extra_bed_policy")
    private boolean extra_bed_policy;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

    @Column(name = "type_of_hotel")
    private String type_of_hotel;

    @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<HotelImage> hotelImages;

    @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Room> rooms;

    @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<NearbyAttractions> nearbyAttractions;

    @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<AmenityForHotel> amenityForHotels;

    @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<ReviewHotel> reviews;
}

