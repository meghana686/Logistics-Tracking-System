package com.logistics.tracking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.logistics.tracking.dto.ResponseStructure;
import com.logistics.tracking.entity.DeliveryAgent;
import com.logistics.tracking.service.DeliveryAgentService;

@RestController
public class DeliveryAgentController {
	@Autowired
	private DeliveryAgentService deliveryAgentService;
	@PostMapping("/delivery-agents")
	public ResponseEntity<ResponseStructure<DeliveryAgent>> saveDeliveryAgent(@RequestBody DeliveryAgent deliveryAgent){
		ResponseStructure<DeliveryAgent> res= deliveryAgentService.saveDeliveryAgent(deliveryAgent);
		return new ResponseEntity<>(res,HttpStatus.CREATED);
		
	}
	@GetMapping("/delivery-agents")
	public ResponseEntity<ResponseStructure<List<DeliveryAgent>>> getAllDeliveryAgents(){
		ResponseStructure<List<DeliveryAgent>> res= deliveryAgentService.getAllDeliveryAgents();
		return new ResponseEntity<>(res,HttpStatus.OK);
		
	}
	@GetMapping("/delivery-agents/{id}")
	public ResponseEntity<ResponseStructure<DeliveryAgent>> getDeliveryAgentById(@PathVariable Integer id){
		ResponseStructure<DeliveryAgent> res= deliveryAgentService.getDeliveryAgentById(id);
		return new ResponseEntity<>(res,HttpStatus.OK);
	}
	@GetMapping("/delivery-agents/vehicle/{vehicleNumber}")
	public ResponseEntity<ResponseStructure<DeliveryAgent>> getDeliveryAgentByVehicleNumber(@PathVariable String vehicleNumber){
		ResponseStructure<DeliveryAgent> res= deliveryAgentService.getDeliveryAgentByVehicleNumber( vehicleNumber);
		return new ResponseEntity<>(res,HttpStatus.OK);
	}
	@GetMapping("/delivery-agents/contact/{contactNumber}")
	public ResponseEntity<ResponseStructure<DeliveryAgent>> getDeliveryAgentByContact(@PathVariable String contactNumber){
		ResponseStructure<DeliveryAgent> res= deliveryAgentService.getDeliveryAgentByContactNumber(contactNumber);
		return new ResponseEntity<>(res,HttpStatus.OK);
	}
	@PutMapping("/delivery-agents/{id}")
	public ResponseEntity<ResponseStructure<DeliveryAgent>> updateDeliveryAgent(@PathVariable Integer id,@RequestBody DeliveryAgent deliveryAgent){
		ResponseStructure<DeliveryAgent> res= deliveryAgentService.updateDeliveryAgent(id,deliveryAgent);
		return new ResponseEntity<>(res,HttpStatus.OK);
	}
	@DeleteMapping("/delivery-agents/{id}")
	public ResponseEntity<ResponseStructure<String>> deleteDeliveryAgent(@PathVariable Integer id){
		ResponseStructure<String> res= deliveryAgentService.deleteDeliveryAgent(id);
		return new ResponseEntity<>(res,HttpStatus.OK);
	}
	@PutMapping("/delivery-agents/availability/{id}/{availability}")
	public ResponseEntity<ResponseStructure<DeliveryAgent>> updateDeliveryAgentByAvailabilty(@PathVariable Integer id,@PathVariable Boolean  availability){
		ResponseStructure<DeliveryAgent> res= deliveryAgentService.updateAvailability(id,availability);
		return new ResponseEntity<>(res,HttpStatus.OK);
	}
	@GetMapping("/delivery-agents/rating/{rating}")
	public ResponseEntity<ResponseStructure<List<DeliveryAgent>>>  getDeliveryAgentsByRatingGreaterThan(@PathVariable Double rating){
		ResponseStructure<List<DeliveryAgent>> res= deliveryAgentService. getDeliveryAgentsByRatingGreaterThan(rating);
		return new ResponseEntity<>(res,HttpStatus.OK);
	}
	@GetMapping("/delivery-agents/sort/rating")
	public ResponseEntity<ResponseStructure<List<DeliveryAgent>>> sortByRating(){
		ResponseStructure<List<DeliveryAgent>> res= deliveryAgentService.sortByRating();
		return new ResponseEntity<>(res,HttpStatus.OK);
	}
	
	
		


}
