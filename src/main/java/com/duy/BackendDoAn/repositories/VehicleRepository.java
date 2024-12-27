package com.duy.BackendDoAn.repositories;

import com.duy.BackendDoAn.models.Vehicle;
import org.springframework.cglib.core.Local;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalTime;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    @Query("SELECT DISTINCT v FROM Vehicle v " +
            "JOIN v.vehicleRentalFacilities rv " +
            "JOIN rv.rentalFacility rf " +
            "JOIN rf.offices o " +
            "JOIN o.attraction a " +
            "JOIN a.city c " +
            "WHERE v.active = true AND (:location IS NULL OR c.city_name LIKE %:location%)")
    Page<Vehicle> searchVehicle(
            @Param("location") String location,
            Pageable pageable
    );
}
