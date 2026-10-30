package com.logistics.tracking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.logistics.tracking.dto.ResponseStructure;
import com.logistics.tracking.entity.TrackingHistory;
import com.logistics.tracking.enums.TrackingStatus;
import com.logistics.tracking.service.TrackingHistoryService;

@RestController
public class TrackingHistoryController {
	@Autowired
	private TrackingHistoryService trackingHistoryService;
	@PostMapping("/tracking-histories")
	public ResponseEntity<ResponseStructure<TrackingHistory>> saveTrackingHistory(@RequestBody TrackingHistory trackingHistory){
		ResponseStructure<TrackingHistory> res=trackingHistoryService.saveTrackingHistory(trackingHistory);
		return new ResponseEntity<>(res,HttpStatus.CREATED);		
	}
	@GetMapping("/tracking-histories")
	public ResponseEntity<ResponseStructure<List<TrackingHistory>>> getAllTrackingHistories(){
		ResponseStructure<List<TrackingHistory>> res=trackingHistoryService.getAllTrackingHistories();
		return new ResponseEntity<>(res,HttpStatus.OK);		
	}
	@GetMapping("/tracking-histories/{id}")
	public ResponseEntity<ResponseStructure<TrackingHistory>>  getTrackingHistoryById(@PathVariable Integer id){
		ResponseStructure<TrackingHistory> res=trackingHistoryService.getTrackingHistoryById(id);
		return new ResponseEntity<>(res,HttpStatus.OK);		
	}
	@GetMapping("/tracking-histories/tracking/{trackingNumber}")
	public ResponseEntity<ResponseStructure<List<TrackingHistory>>> getTrackingHistoryByTrackingNumber(
	        @PathVariable String trackingNumber){
		ResponseStructure<List<TrackingHistory>> res=trackingHistoryService.getTrackingHistoryByTrackingNumber(trackingNumber);
		return new ResponseEntity<>(res,HttpStatus.OK);	
	}
	@GetMapping("/tracking-histories/status/{status}")
	public ResponseEntity<ResponseStructure<List<TrackingHistory>>> getTrackingHistoryByStatus(
	        @PathVariable TrackingStatus status){
		ResponseStructure<List<TrackingHistory>> res=trackingHistoryService.getTrackingHistoryByStatus(status);
		return new ResponseEntity<>(res,HttpStatus.OK);	
	}
	@GetMapping("/tracking-histories/shipment/{id}")
	public ResponseEntity<ResponseStructure<List<TrackingHistory>>> getAllTrackingHistoryOfShipments(
	        @PathVariable Integer id){
		ResponseStructure<List<TrackingHistory>> res=trackingHistoryService.getAllTrackingHistoryOfShipments(id);
		return new ResponseEntity<>(res,HttpStatus.OK);	
	}
	

}
