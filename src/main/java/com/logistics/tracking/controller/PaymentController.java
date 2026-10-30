package com.logistics.tracking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.logistics.tracking.dto.ResponseStructure;
import com.logistics.tracking.entity.Payment;
import com.logistics.tracking.enums.PaymentStatus;
import com.logistics.tracking.service.PaymentService;

@RestController
public class PaymentController {
@Autowired
private PaymentService paymentService;
@PostMapping("/payments")
public ResponseEntity<ResponseStructure<Payment>> savePayment(@RequestBody Payment payment){
	ResponseStructure<Payment> res=paymentService.savePayment(payment);
	return new ResponseEntity<>(res,HttpStatus.CREATED);
}
@GetMapping("/payments")
public ResponseEntity<ResponseStructure<List<Payment>>> getAllPayments(){
	ResponseStructure<List<Payment>> res=paymentService.getAllPayments();
	return new ResponseEntity<>(res,HttpStatus.OK);
}
@GetMapping("/payments/{id}")
public ResponseEntity<ResponseStructure<Payment>> getPaymentById(@PathVariable Integer id){
	ResponseStructure<Payment> res=paymentService. getPaymentById(id);
	return new ResponseEntity<>(res,HttpStatus.OK);
}
@GetMapping("/payments/customer/{id}")
public ResponseEntity<ResponseStructure<List<Payment>>> getPaymentDetailsByCustomer(Integer id){
	ResponseStructure<List<Payment>> res=paymentService.getPaymentDetailsByCustomer(id);
	return new ResponseEntity<>(res,HttpStatus.OK);
}
@PutMapping("/payments/{paymentId}/{status}")
public ResponseEntity<ResponseStructure<Payment>> updatePaymentStatus(
        @PathVariable Integer paymentId,
        @PathVariable PaymentStatus status){
	ResponseStructure<Payment> res=paymentService.updatePaymentStatus(paymentId, status);
	return new ResponseEntity<>(res,HttpStatus.OK);
}
}