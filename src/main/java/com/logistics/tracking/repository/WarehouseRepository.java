package com.logistics.tracking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.logistics.tracking.entity.Warehouse;

public interface WarehouseRepository  extends JpaRepository<Warehouse, Integer> {
 boolean existsByContactNumber(String contactNumber);
 List<Warehouse> findByLocation(String location);
 List<Warehouse> findAllByOrderByCapacityAsc();
}
