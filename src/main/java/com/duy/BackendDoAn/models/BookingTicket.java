package com.duy.BackendDoAn.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Entity
@Table(name = "booking_ticket")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookingTicket {
    @Id
    private String id;

    @Column(name = "booking_date")
    private LocalDate booking_date;

    @Column(name = "customer_full_name")
    private String customerFullName;

    @Column(name = "customer_email")
    private String customerEmail;

    @Column(name = "customer_phone_number")
    private String customerPhoneNumber;

    @Column(name = "customer_country")
    private String customerCountry;

    @Column(name = "total_price")
    private Long total_price;

    @Column(name = "status")
    private String status;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "bookingTicket", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<BookedTicket> bookedTickets;
}
