package com.duy.BackendDoAn.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "accessory_booking")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccessoryBooking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "price_per")
    private Long price_per;

    @Column(name = "amount")
    private Long amount;

    @ManyToOne
    @JoinColumn(name = "accessory_id")
    @JsonBackReference
    private Accessory accessory;

    @ManyToOne
    @JoinColumn(name = "booking_vehicle_id")
    @JsonBackReference
    private BookingVehicle bookingVehicle;
}
