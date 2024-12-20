package com.duy.BackendDoAn.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "rental_facility")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RentalFacility {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "phone_number")
    private String phone_number;

    @Column(name = "email")
    private String email;

    @Column(name = "description")
    private String description;

    @Column(name = "address")
    private String address;

    @Column(name = "review_count")
    private Long review_count;

    @OneToMany(mappedBy = "rentalFacility", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Office> offices;

    @OneToMany(mappedBy = "rentalFacility", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<VehicleRentalFacility> vehicleRentalFacilities;

    @OneToMany(mappedBy = "rentalFacility", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<ReviewRentalFacility> reviewRentalFacilities;
}
