package com.duy.BackendDoAn.repositories;

import com.duy.BackendDoAn.models.BookingVehicle;
import com.duy.BackendDoAn.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingVehicleRepository extends JpaRepository<BookingVehicle, String> {
    Page<BookingVehicle> findByUser(User user, Pageable pageable);
}
