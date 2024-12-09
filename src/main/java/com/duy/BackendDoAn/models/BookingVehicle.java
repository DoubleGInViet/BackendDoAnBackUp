package com.duy.BackendDoAn.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "booking_vehicle")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookingVehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "booking_date")
    private LocalDate booking_date;

    @Column(name = "start_date")
    private LocalDate start_date;

    @Column(name = "start_time")
    private LocalTime start_time;

    @Column(name = "start_address")
    private String start_address;

    @Column(name = "return_date")
    private LocalDate return_date;

    @Column(name = "return_time")
    private LocalTime return_time;

    @Column(name = "return_address")
    private String return_address;

    @Column(name = "total_price")
    private Float total_price;

    @ManyToOne
    @JoinColumn(name = "rental_vehicle_id")
    @JsonBackReference
    private VehicleRentalFacility vehicleRentalFacility;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "bookingVehicle", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<AccessoryBooking> accessoryBookings;
}
