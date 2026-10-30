package com.logistics.tracking.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.logistics.tracking.dto.ResponseStructure;
import com.logistics.tracking.entity.DeliveryAgent;
import com.logistics.tracking.entity.Shipment;
import com.logistics.tracking.entity.Warehouse;
import com.logistics.tracking.enums.ShipmentStatus;
import com.logistics.tracking.exception.IdNotFoundException;
import com.logistics.tracking.exception.NoRecordAvailableException;
import com.logistics.tracking.repository.CustomerRepository;
import com.logistics.tracking.repository.DeliveryAgentRepository;
import com.logistics.tracking.repository.PackageEntityRepository;
import com.logistics.tracking.repository.PaymentRepository;
import com.logistics.tracking.repository.ShipmentRepository;
import com.logistics.tracking.repository.TrackingHistoryRepository;
import com.logistics.tracking.repository.WarehouseRepository;

@Service
public class ShipmentService {
	@Autowired
	private ShipmentRepository shipmentRepository ;
	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private DeliveryAgentRepository deliveryAgentRepository;

	@Autowired
	private WarehouseRepository warehouseRepository;
	@Autowired
	private PaymentRepository paymentRepository;

	@Autowired
	private PackageEntityRepository packageEntityRepository;

	@Autowired
	private TrackingHistoryRepository trackingHistoryRepository;
	public ResponseStructure<Shipment> saveShipment(Shipment shipment){
		if(shipment.getTrackingNumber()==null||shipment.getTrackingNumber().isBlank()) {
			throw new IllegalArgumentException("tracking number is required");
		}
		if (shipment.getCustomer() == null ||
			    !customerRepository.existsById(shipment.getCustomer().getId())) {

			    throw new IllegalArgumentException("Customer does not exist");
			}
		if (shipment.getDeliveryAgent() == null ||
			    !deliveryAgentRepository.existsById(shipment.getDeliveryAgent().getId())) {

			    throw new IllegalArgumentException("Delivery agent does not exist");
			}
		if (deliveryAgentRepository.findById(shipment.getDeliveryAgent().getId())
		        .get().getAvailabilityStatus() == false) {

		    throw new IllegalArgumentException("Delivery agent is not available");
		}
		if (shipment.getWarehouse() == null ||
			    !warehouseRepository.existsById(shipment.getWarehouse().getId())) {

			    throw new IllegalArgumentException("Warehouse does not exist");
			}
		if (shipment.getWeight() == null || shipment.getWeight() <= 0) {
		    throw new IllegalArgumentException("Weight must be greater than zero");
		}

		double amount = 50.0 + (shipment.getWeight() * 20.0);

		if (shipment.getPackageEntity() != null &&
		        Boolean.TRUE.equals(shipment.getPackageEntity().getFragile())) {
		    amount += 30.0;
		}
		DeliveryAgent agent = deliveryAgentRepository
		        .findById(shipment.getDeliveryAgent().getId())
		        .orElseThrow(() ->
		                new IllegalArgumentException("Delivery agent does not exist"));

		shipment.setDeliveryAgent(agent);
		if (shipment.getPayment() == null ||
		        shipment.getPayment().getId() == null ||
		        !paymentRepository.existsById(shipment.getPayment().getId())) {

		    throw new IllegalArgumentException("Payment does not exist");
		}
		if (shipment.getPackageEntity() == null ||
		        shipment.getPackageEntity().getId() == null ||
		        !packageEntityRepository.existsById(shipment.getPackageEntity().getId())) {

		    throw new IllegalArgumentException("Package entity does not exist");
		}
		if (shipment.getTrackingHistories() == null ||
		        shipment.getTrackingHistories().isEmpty()) {

		    throw new IllegalArgumentException("Tracking history does not exist");
		}
		Shipment newShipment = shipmentRepository.save(shipment);
        ResponseStructure<Shipment> res=new ResponseStructure<>();
		res.setData(newShipment);
		res.setStatusCode(HttpStatus.CREATED.value());
		res.setMessage("Shipment record inserted successfully");

		return res;

		
	}
	public ResponseStructure<List<Shipment>> getAllShipments(){
		List<Shipment> shipments=shipmentRepository.findAll();
		if(shipments.isEmpty()) {
			throw new NoRecordAvailableException("No Records available in the shipment");
		}
		ResponseStructure<List<Shipment>> res=new ResponseStructure<>();
		res.setData(shipments);
		res.setStatusCode(HttpStatus.OK.value());
		res.setMessage("Shipment records retrieved successfully");

		return res;
	}
	public ResponseStructure<Shipment> getShipmentById(Integer id){
		Optional <Shipment> shipment=shipmentRepository.findById(id);
		if(shipment.isEmpty()) {
			throw new IdNotFoundException("shipmnet is not found with the given Id: "+id);
		}
		ResponseStructure<Shipment> res=new ResponseStructure<>();
		res.setData(shipment.get());
		res.setStatusCode(HttpStatus.OK.value());
		res.setMessage("Shipment record retrieved successfully");

		return res;
	}
	public ResponseStructure<Shipment> getShipmentByTrackingNumber(String trackingNumber){
		Optional <Shipment> shipment=shipmentRepository.findByTrackingNumber(trackingNumber);
		if(shipment.isEmpty()) {
			throw new IdNotFoundException("shipment is not found with the given Tracking Number: "+trackingNumber);
		}
		ResponseStructure<Shipment> res=new ResponseStructure<>();
		res.setData(shipment.get());
		res.setStatusCode(HttpStatus.OK.value());
		res.setMessage("Shipment record retrieved successfully");

		return res;
	}
	public ResponseStructure<Shipment> updateShipmentStatus(Integer id, ShipmentStatus status) {
	    Optional<Shipment> shipment = shipmentRepository.findById(id);

	    if (shipment.isEmpty()) {
	        throw new IdNotFoundException("Shipment is not found with the given id: " + id);
	    }

	    Shipment newShipment = shipment.get();
	    newShipment.setStatus(status);
	    Shipment updatedShipment = shipmentRepository.save(newShipment);

	    ResponseStructure<Shipment> res = new ResponseStructure<>();
	    res.setData(updatedShipment);
	    res.setStatusCode(HttpStatus.OK.value());
	    res.setMessage("Shipment status updated successfully");

	    return res;
	}
	public ResponseStructure<List<Shipment>> getShipmentsByCustomerId(Integer customerId){
		List<Shipment> shipments=shipmentRepository.findByCustomer_Id(customerId);
		if(shipments.isEmpty()) {
			throw new NoRecordAvailableException("No Records available in the shipment");
		}
		ResponseStructure<List<Shipment>> res=new ResponseStructure<>();
		res.setData(shipments);
		res.setStatusCode(HttpStatus.OK.value());
		res.setMessage("Shipment records retrieved successfully");

		return res;
	}
	public ResponseStructure<List<Shipment>> getShipmentsByWarehouseId(Integer warehouseId){
		List<Shipment> shipments=shipmentRepository.findByWarehouse_Id(warehouseId);
		if(shipments.isEmpty()) {
			throw new NoRecordAvailableException("No Records available in the shipment");
		}
		ResponseStructure<List<Shipment>> res=new ResponseStructure<>();
		res.setData(shipments);
		res.setStatusCode(HttpStatus.OK.value());
		res.setMessage("Shipment records retrieved successfully");

		return res;
	}
	public ResponseStructure<List<Shipment>> getShipmentsByDeliveryAgentId(Integer agentId){
		List<Shipment> shipments=shipmentRepository.findByDeliveryAgent_Id(agentId);
		if(shipments.isEmpty()) {
			throw new NoRecordAvailableException("No Records available in the shipment");
		}
		ResponseStructure<List<Shipment>> res=new ResponseStructure<>();
		res.setData(shipments);
		res.setStatusCode(HttpStatus.OK.value());
		res.setMessage("Shipment records retrieved successfully");

		return res;
	}
	public ResponseStructure<List<Shipment>> getShipmentsBySourceAndDestination(String source,String destination){
		List<Shipment> shipments=shipmentRepository.findBySourceAndDestination(source,destination);
		if(shipments.isEmpty()) {
			throw new NoRecordAvailableException("No Records available in the shipment");
		}
		ResponseStructure<List<Shipment>> res=new ResponseStructure<>();
		res.setData(shipments);
		res.setStatusCode(HttpStatus.OK.value());
		res.setMessage("Shipment records retrieved successfully");

		return res;
	}
	public ResponseStructure<List<Shipment>> getShipmentsByDeliveryDate(LocalDate deliveryDate){
		List<Shipment> shipments=shipmentRepository.findByDeliveryDate(deliveryDate);
		if(shipments.isEmpty()) {
			throw new NoRecordAvailableException("No Records available in the shipment");
		}
		ResponseStructure<List<Shipment>> res=new ResponseStructure<>();
		res.setData(shipments);
		res.setStatusCode(HttpStatus.OK.value());
		res.setMessage("Shipment records retrieved successfully");

		return res;
	}
	public ResponseStructure<Page<Shipment>> getByPaginationAndSorting(int pageNumber, int pageSize,String feildName){
		Page<Shipment> page=shipmentRepository.findAll(PageRequest.of(pageNumber, pageSize,Sort.by(feildName).descending()));
		if(page.isEmpty()) {
			throw new NoRecordAvailableException("NO records available in the shipmnet");
		}
		ResponseStructure<Page<Shipment>> res=new ResponseStructure<>();
		res.setData(page);
		res.setStatusCode(HttpStatus.OK.value());
		res.setMessage("Shipment records retrieved successfully");

		return res;	
	}
	  public ResponseStructure<String> deleteShipment(Integer id) {
		  Optional<Shipment>existingshipment  =shipmentRepository.findById(id);
		  if(existingshipment.isEmpty()) {
			  throw new IdNotFoundException("Shipment is not found with given id "+id);
		  }

		   shipmentRepository.delete(existingshipment.get());
		    ResponseStructure<String> res = new ResponseStructure<>();
		    res.setData("Shipment deleted successfully");
		    res.setMessage("Shipment deleted successfully");
		    res.setStatusCode(HttpStatus.OK.value());
		    return res;

	  }
	  public ResponseStructure<Shipment> updateShipmentDestination(Integer id, String destination) {
		    Optional<Shipment> shipment = shipmentRepository.findById(id);

		    if (shipment.isEmpty()) {
		        throw new IdNotFoundException("Shipment is not found with the given id: " + id);
		    }

		    Shipment newShipment = shipment.get();
		    newShipment.setDestination(destination);
		    Shipment updatedShipment = shipmentRepository.save(newShipment);

		    ResponseStructure<Shipment> res = new ResponseStructure<>();
		    res.setData(updatedShipment);
		    res.setStatusCode(HttpStatus.OK.value());
		    res.setMessage("Shipment destination updated successfully");

		    return res;
	  }
	  public ResponseStructure<Shipment> assignDeliveryAgent(Integer shipmentId, Integer agentId){
		  Optional<Shipment> shipment = shipmentRepository.findById(shipmentId);

		    if (shipment.isEmpty()) {
		        throw new IdNotFoundException("Shipment is not found with the given id: " + shipmentId);
		    }
		    Optional<DeliveryAgent> deliveryAgent=deliveryAgentRepository.findById(agentId);
		    if(deliveryAgent.isEmpty()) {
		    	throw new IdNotFoundException("DeliveryAgent is not found with the given id: " + agentId);
				  
		    }
		    Shipment existingShipment = shipment.get();

		    existingShipment.setDeliveryAgent(deliveryAgent.get());

		    Shipment updatedShipment = shipmentRepository.save(existingShipment);
		    ResponseStructure<Shipment> res = new ResponseStructure<>();
		    res.setData(updatedShipment);
		    res.setStatusCode(HttpStatus.OK.value());
		    res.setMessage("Shipment deliveryAgent updated successfully");

		    return res;
	  }
	  public ResponseStructure<Shipment> assignWarehouse(Integer shipmentId, Integer warehouseId){
		  Optional<Shipment> shipment = shipmentRepository.findById(shipmentId);

		    if (shipment.isEmpty()) {
		        throw new IdNotFoundException("Shipment is not found with the given id: " + shipmentId);
		    }
		    Optional<Warehouse> warehouse=warehouseRepository.findById(warehouseId);
		    if(warehouse.isEmpty()) {
		    	throw new IdNotFoundException("warehouse is not found with the given id: " + warehouseId);
				  
		    }
		    Shipment existingShipment = shipment.get();

		    existingShipment.setWarehouse(warehouse.get());

		    Shipment updatedShipment = shipmentRepository.save(existingShipment);
		   
		    ResponseStructure<Shipment> res = new ResponseStructure<>();
		    res.setData(updatedShipment);
		    res.setStatusCode(HttpStatus.OK.value());
		    res.setMessage("Shipment warehouse  updated successfully");

		    return res;
	  }
		    
	  
}
