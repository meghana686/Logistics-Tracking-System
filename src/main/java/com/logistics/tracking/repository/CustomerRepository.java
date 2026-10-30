package com.logistics.tracking.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.logistics.tracking.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
	boolean existsByEmail(String email);
	boolean existsByContact(String contact);
	Optional<Customer> findByEmail(String email);
	Optional<Customer> findByContact(String contact);

}
