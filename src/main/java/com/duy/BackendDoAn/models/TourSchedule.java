package com.duy.BackendDoAn.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "tour_schedule")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TourSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "happen_date")
    private LocalDate happenDate;

    @Column(name = "start_time")
    private LocalTime startTime;

    @Column(name = "end_time")
    private LocalTime endTime;

    @ManyToOne
    @JoinColumn(name = "tour_id")
    @JsonBackReference
    private Tour tour;

    @OneToMany(mappedBy = "tourSchedule", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<BookingTicket> bookingTickets;

    @OneToMany(mappedBy = "tourSchedule", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<DailyTicketAvailability> dailyTicketAvailabilities;
}
