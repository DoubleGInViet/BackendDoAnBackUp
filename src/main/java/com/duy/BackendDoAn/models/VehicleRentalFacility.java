package com.duy.BackendDoAn.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "rental_vehicle")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VehicleRentalFacility {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "vehicle_id")
    @JsonBackReference
    private Vehicle vehicle;

    @ManyToOne
    @JoinColumn(name = "rental_facility_id")
    @JsonBackReference
    private RentalFacility rentalFacility;

    @Column(name = "stake")
    private Long stake;

    @Column(name = "available_vehicle")
    private Long availableVehicle;

    @Column(name = "price")
    private Long price;

    @OneToMany(mappedBy = "vehicleRentalFacility", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<BookingVehicle> bookingVehicles;
}
