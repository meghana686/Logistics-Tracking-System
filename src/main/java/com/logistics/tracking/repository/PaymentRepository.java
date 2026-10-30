package com.logistics.tracking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.logistics.tracking.entity.Payment;

public interface PaymentRepository  extends JpaRepository<Payment, Integer> {
	List<Payment> findByShipment_Customer_Id(Integer id);
}
