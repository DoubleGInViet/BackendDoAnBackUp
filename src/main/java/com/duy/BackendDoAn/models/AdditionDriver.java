package com.duy.BackendDoAn.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "addition_driver")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AdditionDriver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private int title;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "phone")
    private String phone;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "booking_vehicle_id")
    private BookingVehicle bookingVehicle;
}
