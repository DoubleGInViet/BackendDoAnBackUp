package com.duy.BackendDoAn.repositories;

import com.duy.BackendDoAn.models.RentalFacility;
import com.duy.BackendDoAn.models.Vehicle;
import com.duy.BackendDoAn.models.VehicleRentalFacility;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VehicleRentalFacilityRepository extends JpaRepository<VehicleRentalFacility, Long> {
    Optional<VehicleRentalFacility> findByVehicleAndRentalFacility(Vehicle vehicle, RentalFacility rentalFacility);
    Optional<VehicleRentalFacility> findByVehicle_IdAndRentalFacility_Id(Long vehicleId, Long rentalFacilityId);
}
