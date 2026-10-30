package com.logistics.tracking.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.logistics.tracking.dto.ResponseStructure;
import com.logistics.tracking.entity.Payment;
import com.logistics.tracking.enums.PaymentStatus;
import com.logistics.tracking.exception.IdNotFoundException;
import com.logistics.tracking.exception.NoRecordAvailableException;
import com.logistics.tracking.repository.PaymentRepository;

@Service
public class PaymentService {
@Autowired
private PaymentRepository paymentRepository ;
public ResponseStructure<Payment> savePayment(Payment payment){
	Payment newPayment=paymentRepository.save(payment);
	ResponseStructure<Payment> res=new ResponseStructure<>();
	res.setData(newPayment);
	res.setMessage("payment record inserted successfully");
	res.setStatusCode(HttpStatus.CREATED.value());
	return res;
}
public ResponseStructure<List<Payment>> getAllPayments(){
	List<Payment> newPayment=paymentRepository.findAll();
	if(newPayment.isEmpty()) {
		throw new NoRecordAvailableException("no records in payment table");
	}
	ResponseStructure<List<Payment>> res=new ResponseStructure<>();
	res.setData(newPayment);
	res.setMessage("payment record retrived successfully");
	res.setStatusCode(HttpStatus.OK.value());
	return res;
}

public ResponseStructure<Payment> getPaymentById(Integer id){
	Optional<Payment> payment=paymentRepository.findById(id);
	if(payment.isEmpty()) {
		throw new IdNotFoundException("payment is not found with the given id :"+id);
	}
	ResponseStructure<Payment> res=new ResponseStructure<>();
	res.setData(payment.get());
	res.setMessage("payment record retrived successfully");
	res.setStatusCode(HttpStatus.OK.value());
	return res;
}
public ResponseStructure<List<Payment>> getPaymentDetailsByCustomer(Integer id){
	List<Payment> newPayment=paymentRepository.findByShipment_Customer_Id(id);
	if(newPayment.isEmpty()) {
		throw new NoRecordAvailableException("no records in payment table");
	}
	ResponseStructure<List<Payment>> res=new ResponseStructure<>();
	res.setData(newPayment);
	res.setMessage("payment record retrived successfully");
	res.setStatusCode(HttpStatus.OK.value());
	return res;
}
public ResponseStructure<Payment> updatePaymentStatus(Integer paymentId, PaymentStatus status){
	Optional<Payment> payment=paymentRepository.findById(paymentId);
	if(payment.isEmpty()) {
		throw new IdNotFoundException("payment is not found with the given id :"+paymentId);
	}
	Payment newPayment=payment.get();
	newPayment.setPaymentStatus(status);
	Payment existingPayment=paymentRepository.save(newPayment);
	ResponseStructure<Payment> res=new ResponseStructure<>();
	res.setData(existingPayment);
	res.setMessage("payment record retrived successfully");
	res.setStatusCode(HttpStatus.OK.value());
	return res;
}
}
