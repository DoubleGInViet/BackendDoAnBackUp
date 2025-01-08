package com.duy.BackendDoAn.models;

import com.duy.BackendDoAn.models.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "booking_room")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookingRoom {
    @Id
    private String id;

    @Column(name = "booking_date")
    private LocalDateTime booking_date;

    @Column(name = "adults")
    private Long adults;

    @Column(name = "children")
    private Long children;

    @Column(name = "check_in_date")
    private LocalDate check_in_date;

    @Column(name = "check_out_date")
    private LocalDate check_out_date;

    @Column(name = "total_price")
    private Long total_price;

    @Column(name = "total_rooms")
    private Long total_rooms;

    @Column(name = "customer_full_name")
    private String customerFullName;

    @Column(name = "customer_email")
    private String customerEmail;

    @Column(name = "customer_phone_number")
    private String customerPhoneNumber;

    @Column(name = "customer_country")
    private String customerCountry;

    @Column(name = "special_request")
    private String specialRequest;

    @Column(name = "arrival_time")
    private String arrivalTime;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "status")
    private String status;

    @OneToMany(mappedBy = "bookingRoom", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<BookedRoom> bookedRooms;

    public Hotel getHotel() {
        if (bookedRooms != null && !bookedRooms.isEmpty()) {
            BookedRoom bookedRoom = bookedRooms.get(0);
            return bookedRoom.getRoom().getHotel();
        }
        return null;
    }
}
