package com.logistics.tracking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.logistics.tracking.entity.TrackingHistory;
import com.logistics.tracking.enums.TrackingStatus;

public interface TrackingHistoryRepository  extends JpaRepository<TrackingHistory, Integer> {
	List<TrackingHistory> findByShipment_TrackingNumber(String trackingNumber);
	List<TrackingHistory> findByStatus(TrackingStatus status);
	List<TrackingHistory> findByShipment_Id(Integer shipmentId);
}
