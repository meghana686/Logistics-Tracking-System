package com.logistics.tracking.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.logistics.tracking.dto.ResponseStructure;
import com.logistics.tracking.entity.DeliveryAgent;
import com.logistics.tracking.exception.IdNotFoundException;
import com.logistics.tracking.exception.NoRecordAvailableException;
import com.logistics.tracking.repository.DeliveryAgentRepository;
import com.logistics.tracking.repository.ShipmentRepository;

@Service
public class DeliveryAgentService {
  @Autowired
  private DeliveryAgentRepository deliveryAgentRepository ;
  @Autowired
  private  ShipmentRepository shipmentRepository;
  public ResponseStructure< DeliveryAgent> saveDeliveryAgent( DeliveryAgent deliveryAgent){
	  if(deliveryAgent.getName()==null||deliveryAgent.getName().isBlank()) {
		  throw new IllegalArgumentException("delivery agent is required");
	  }
	  if(deliveryAgent.getContactNumber()==null||deliveryAgent.getContactNumber().isBlank()) {
		  throw new IllegalArgumentException("delivery agent mobile number is required");
	  }
	  if (!deliveryAgent.getContactNumber().matches("[0-9]{10}")) {
		    throw new IllegalArgumentException("Contact number should contain exactly 10 digits");
		}
	  if (deliveryAgentRepository.existsByContactNumber(deliveryAgent.getContactNumber())) {
		    throw new IllegalArgumentException("Contact number already exists");
		}
	  if(deliveryAgent.getVehicleNumber()==null||deliveryAgent.getVehicleNumber().isBlank()) {
		  throw new IllegalArgumentException("delivery agent vehicle number is required");
	  }
	  if (deliveryAgentRepository.existsByVehicleNumber(deliveryAgent.getVehicleNumber())) {
		    throw new IllegalArgumentException("Vehicle  number already exists");
		}
	  DeliveryAgent deliveryAgent1= deliveryAgentRepository.save(deliveryAgent);
	  ResponseStructure<DeliveryAgent> res=new ResponseStructure<>();
	  res.setData(deliveryAgent1);
	  res.setMessage("delevery agent successfully added");
	  res.setStatusCode(HttpStatus.CREATED.value());
	  return res;
  }
  public ResponseStructure<List< DeliveryAgent>> getAllDeliveryAgents(){
	  List< DeliveryAgent> deliveryAgents  =deliveryAgentRepository.findAll();
	  if(deliveryAgents.isEmpty()) {
		  throw new NoRecordAvailableException("No records available for delivery agents");
	  }
	  ResponseStructure<List<DeliveryAgent>> res=new ResponseStructure<>();
	  res.setData(deliveryAgents);
	  res.setMessage("Delivery agents retrieved successfullyd");
	  res.setStatusCode(HttpStatus.OK.value());
	  return res;
	  
  }
  public ResponseStructure< DeliveryAgent> getDeliveryAgentById(Integer id){
	  Optional< DeliveryAgent> deliveryAgent  =deliveryAgentRepository.findById(id);
	  if(deliveryAgent.isEmpty()) {
		  throw new IdNotFoundException("Delivery agent is not found with given id "+id);
	  }
	  ResponseStructure<DeliveryAgent> res=new ResponseStructure<>();
	  res.setData(deliveryAgent.get());
	  res.setMessage("Delivery agent retrieved successfullyd");
	  res.setStatusCode(HttpStatus.OK.value());
	  return res;
	  
  }
  public ResponseStructure< DeliveryAgent> getDeliveryAgentByVehicleNumber(String vehicleNumber){
	  Optional< DeliveryAgent> deliveryAgent  =deliveryAgentRepository.findByVehicleNumber(vehicleNumber);
	  if(deliveryAgent.isEmpty()) {
		  throw new IdNotFoundException("Delivery agent is not found with given vehicle number "+vehicleNumber);
	  }
	  ResponseStructure<DeliveryAgent> res=new ResponseStructure<>();
	  res.setData(deliveryAgent.get());
	  res.setMessage("Delivery agent retrieved successfullyd");
	  res.setStatusCode(HttpStatus.OK.value());
	  return res;
	  
  }
  public ResponseStructure<DeliveryAgent> getDeliveryAgentByContactNumber(String contact){
	  Optional< DeliveryAgent> deliveryAgent  =deliveryAgentRepository.findByContactNumber(contact);
	  if(deliveryAgent.isEmpty()) {
		  throw new IdNotFoundException("Delivery agent is not found with given  contact "+contact);
	  }
	  ResponseStructure<DeliveryAgent> res=new ResponseStructure<>();
	  res.setData(deliveryAgent.get());
	  res.setMessage("Delivery agent retrieved successfullyd");
	  res.setStatusCode(HttpStatus.OK.value());
	  return res;
	  
  }
  public ResponseStructure<DeliveryAgent> updateDeliveryAgent(
	        Integer id, DeliveryAgent deliveryAgent) {
	  Optional< DeliveryAgent>existingAgent  =deliveryAgentRepository.findById(id);
	  if(existingAgent.isEmpty()) {
		  throw new IdNotFoundException("Delivery agent is not found with given id "+id);
	  }
	  DeliveryAgent agent = existingAgent.get();

	    
	    agent.setName(deliveryAgent.getName());
	    agent.setVehicleNumber(deliveryAgent.getVehicleNumber());
	    agent.setAvailabilityStatus(deliveryAgent.getAvailabilityStatus());
	    agent.setContactNumber(deliveryAgent.getContactNumber());
	    agent.setRating(deliveryAgent.getRating());

	    DeliveryAgent updatedAgent = deliveryAgentRepository.save(agent);

	    ResponseStructure<DeliveryAgent> res = new ResponseStructure<>();
	    res.setData(updatedAgent);
	    res.setMessage("Delivery agent updated successfully");
	    res.setStatusCode(HttpStatus.OK.value());
	    return res;
	    
	}
  public ResponseStructure<String> deleteDeliveryAgent(Integer id) {
	  Optional< DeliveryAgent>existingAgent  =deliveryAgentRepository.findById(id);
	  if(existingAgent.isEmpty()) {
		  throw new IdNotFoundException("Delivery agent is not found with given id "+id);
	  }
	  boolean shipmentExists = shipmentRepository.existsByDeliveryAgent_Id(id);

	    if (shipmentExists) {
	        throw new IllegalStateException(
	                "Cannot delete delivery agent because shipments are assigned to this agent");
	    }
	    deliveryAgentRepository.delete(existingAgent.get());
	    ResponseStructure<String> res = new ResponseStructure<>();
	    res.setData("Delivery agent deleted successfully");
	    res.setMessage("Delivery agent deleted successfully");
	    res.setStatusCode(HttpStatus.OK.value());
	    return res;

  }
  public ResponseStructure<DeliveryAgent> updateAvailability(Integer id, Boolean availability) {
	  Optional< DeliveryAgent>existingAgent  =deliveryAgentRepository.findById(id);
	  if(existingAgent.isEmpty()) {
		  throw new IdNotFoundException("Delivery agent is not found with given id "+id);
	  }
	  DeliveryAgent agent = existingAgent.get();
	  agent.setAvailabilityStatus(availability);
	  DeliveryAgent updatedAgent = deliveryAgentRepository.save(agent);

	    ResponseStructure<DeliveryAgent> res = new ResponseStructure<>();
	    res.setData(updatedAgent);
	    res.setMessage("Delivery agent updated successfully");
	    res.setStatusCode(HttpStatus.OK.value());
	    return res;
	  
	    
  }
  public ResponseStructure<List<DeliveryAgent>> getDeliveryAgentsByRatingGreaterThan(Double rating) {
	  List< DeliveryAgent> deliveryAgents  =deliveryAgentRepository.findByRatingGreaterThan(rating);
	  if(deliveryAgents.isEmpty()) {
		  throw new NoRecordAvailableException("No records available for delivery agents");
	  }
	  ResponseStructure<List<DeliveryAgent>> res=new ResponseStructure<>();
	  res.setData(deliveryAgents);
	  res.setMessage("Delivery agents retrieved successfully");
	  res.setStatusCode(HttpStatus.OK.value());
	  return res;
  }
  public ResponseStructure<List<DeliveryAgent>> sortByRating(){
	  List< DeliveryAgent> deliveryAgents  =deliveryAgentRepository.findAllByOrderByRatingAsc();
	  if(deliveryAgents.isEmpty()) {
		  throw new NoRecordAvailableException("No records available for delivery agents");
	  }
	  ResponseStructure<List<DeliveryAgent>> res=new ResponseStructure<>();
	  res.setData(deliveryAgents);
	  res.setMessage("Delivery agents retrieved successfully");
	  res.setStatusCode(HttpStatus.OK.value());
	  return res;
  }
	
	
}
