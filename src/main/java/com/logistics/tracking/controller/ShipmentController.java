package com.logistics.tracking.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.logistics.tracking.dto.ResponseStructure;
import com.logistics.tracking.entity.Shipment;
import com.logistics.tracking.enums.ShipmentStatus;
import com.logistics.tracking.service.ShipmentService;

@RestController
public class ShipmentController {
@Autowired
private ShipmentService shipmentService;
@PostMapping("/shipments")
public ResponseEntity<ResponseStructure<Shipment>> saveShipment(@RequestBody Shipment shipment){
	ResponseStructure<Shipment> res=shipmentService.saveShipment(shipment);
	return new ResponseEntity<>(res,HttpStatus.CREATED);
}
@GetMapping("/shipments")
public ResponseEntity<ResponseStructure<List<Shipment>>> getAllShipments(){
	ResponseStructure<List<Shipment>> res=shipmentService.getAllShipments();
	return new ResponseEntity<>(res,HttpStatus.OK);
}
@GetMapping("/shipments/{id}")
public ResponseEntity<ResponseStructure<Shipment>>getShipmentById(@PathVariable Integer id){
	ResponseStructure<Shipment> res=shipmentService.getShipmentById(id);
	return new ResponseEntity<>(res,HttpStatus.OK);
}
@GetMapping("/shipments/tracking/{trackingNumber}")
public ResponseEntity<ResponseStructure<Shipment>>getShipmentByTrackingNumber(@PathVariable String trackingNumber){
	ResponseStructure<Shipment> res=shipmentService.getShipmentByTrackingNumber(trackingNumber);
	return new ResponseEntity<>(res,HttpStatus.OK);
}
@PutMapping("/shipments/{id}/status/{status}")
public ResponseEntity<ResponseStructure<Shipment>> updateShipmentStatus(
        @PathVariable Integer id,
        @PathVariable ShipmentStatus status){
	ResponseStructure<Shipment> res=shipmentService.updateShipmentStatus(id,status);
	return new ResponseEntity<>(res,HttpStatus.OK);
}
@GetMapping("/shipments/customer/{customerId}")
public ResponseEntity<ResponseStructure<List<Shipment>>> getShipmentsByCustomerId(
        @PathVariable Integer customerId) {

    ResponseStructure<List<Shipment>> res =
            shipmentService.getShipmentsByCustomerId(customerId);

    return new ResponseEntity<>(res, HttpStatus.OK);
}
@GetMapping("/shipments/warehouse/{warehouseId}")
public ResponseEntity<ResponseStructure<List<Shipment>>> getShipmentsByWarehouseId(
        @PathVariable Integer warehouseId) {

    ResponseStructure<List<Shipment>> res =
            shipmentService.getShipmentsByWarehouseId(warehouseId);

    return new ResponseEntity<>(res, HttpStatus.OK);
}
@GetMapping("/shipments/deliveryagent/{agentId}")
public ResponseEntity<ResponseStructure<List<Shipment>>> getShipmentsByDeliveryAgentId(
        @PathVariable Integer agentId) {

    ResponseStructure<List<Shipment>> res =
            shipmentService.getShipmentsByDeliveryAgentId(agentId);

    return new ResponseEntity<>(res, HttpStatus.OK);
}
@GetMapping("/shipments/{source}/{destination}")
public ResponseEntity<ResponseStructure<List<Shipment>>>  getShipmentsBySourceAndDestination(
        @PathVariable String source,@PathVariable String destination) {

    ResponseStructure<List<Shipment>> res =
            shipmentService. getShipmentsBySourceAndDestination(source,destination);

    return new ResponseEntity<>(res, HttpStatus.OK);
}
@GetMapping("/shipments/delivery-date/{deliveryDate}")
public ResponseEntity<ResponseStructure<List<Shipment>>>  getShipmentsByDeliveryDate(@PathVariable LocalDate deliveryDate){
    ResponseStructure<List<Shipment>> res =
            shipmentService. getShipmentsByDeliveryDate(deliveryDate);

    return new ResponseEntity<>(res, HttpStatus.OK);
}
@GetMapping("/shipments/{pageNumber}/{pageSize}/{fieldName}")
public ResponseEntity<ResponseStructure<Page<Shipment>>>  getByPaginationAndSorting(@PathVariable int pageNumber, @PathVariable int pageSize,@PathVariable String fieldName){
    ResponseStructure<Page<Shipment>> res =
            shipmentService. getByPaginationAndSorting(pageNumber,pageSize,fieldName);

    return new ResponseEntity<>(res, HttpStatus.OK);
}
@DeleteMapping("/shipment/{id}")
public ResponseEntity<ResponseStructure<String>> deleteShipments(@PathVariable Integer id){
    ResponseStructure<String> res =
            shipmentService. deleteShipment(id);

    return new ResponseEntity<>(res, HttpStatus.OK);
}
@PutMapping("/shipments/{id}/destination/{destination}")
public ResponseEntity<ResponseStructure<Shipment>> updateShipmentDestination(
        @PathVariable Integer id,
        @PathVariable String destination ){
	ResponseStructure<Shipment> res=shipmentService. updateShipmentDestination(id,destination);
	return new ResponseEntity<>(res,HttpStatus.OK);
}
@PutMapping("/shipments/{shipmentId}/delivery-agent/{agentId}")
public ResponseEntity<ResponseStructure<Shipment>> assignDeliveryAgent(
        @PathVariable Integer shipmentId,
        @PathVariable Integer agentId ){
	ResponseStructure<Shipment> res=shipmentService.  assignDeliveryAgent(shipmentId,agentId);
	return new ResponseEntity<>(res,HttpStatus.OK);
}
@PutMapping("/shipments/{shipmentId}/warehouse/{warehouseId}")
public ResponseEntity<ResponseStructure<Shipment>> assignWarehouse(
        @PathVariable Integer shipmentId,
        @PathVariable Integer warehouseId ){
	ResponseStructure<Shipment> res=shipmentService.  assignWarehouse(shipmentId,warehouseId);
	return new ResponseEntity<>(res,HttpStatus.OK);
}

}

