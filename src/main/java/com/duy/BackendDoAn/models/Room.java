package com.duy.BackendDoAn.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "room")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;

    @Column(name = "room_size")
    private Long room_size;

    @Column(name = "max_guests")
    private Long max_guests;

    @Column(name = "type_bed_1")
    private String typeBed1;

    @Column(name = "no_bed_1")
    private Long noBed1;

    @Column(name = "type_bed_2")
    private String typeBed2;

    @Column(name = "no_bed_2")
    private Long noBed2;

    @Column(name = "price")
    private Long price;


    @Column(name = "available_rooms")
    private Long available_room;

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    @JsonBackReference
    private Hotel hotel;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<AmenityForRoom> amenityForRooms;
}
