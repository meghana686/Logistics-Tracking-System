package com.logistics.tracking.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.logistics.tracking.entity.DeliveryAgent;

public interface DeliveryAgentRepository extends JpaRepository<DeliveryAgent, Integer> {
	boolean existsByContactNumber(String contactNumber);

	boolean existsByVehicleNumber(String vehicleNumber);
	Optional<DeliveryAgent> findByVehicleNumber(String vehicleNumber);
	Optional<DeliveryAgent> findByContactNumber(String contactNumber);
	List<DeliveryAgent> findByRatingGreaterThan(Double rating);
	List<DeliveryAgent> findAllByOrderByRatingAsc();
}
