package com.logistics.tracking.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.logistics.tracking.entity.Shipment;

public interface ShipmentRepository extends JpaRepository<Shipment, Integer>{
	boolean existsByTrackingNumber(String trackingNumber);
	boolean existsByDeliveryAgent_Id(Integer id);
	Optional<Shipment> findByTrackingNumber(String trackingNumber);
	List<Shipment> findByCustomer_Id(Integer customerId);
	List<Shipment> findByWarehouse_Id(Integer warehouseId);
	List<Shipment> findByDeliveryAgent_Id(Integer agentId);
	List<Shipment> findBySourceAndDestination(String source,String destination);
	List<Shipment> findByDeliveryDate(LocalDate deliveryDate);
	}

