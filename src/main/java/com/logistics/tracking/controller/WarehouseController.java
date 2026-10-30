package com.logistics.tracking.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.logistics.tracking.entity.Warehouse;
import com.logistics.tracking.service.WarehouseService;

@RestController
public class WarehouseController {
@Autowired
private WarehouseService warehouseService;
@PostMapping("/warehouse")
public ResponseEntity<ResponseStructure<Warehouse>> saveWarehouse(@RequestBody Warehouse warehouse){
	ResponseStructure<Warehouse> res=warehouseService.saveWarehouse(warehouse) ;
	return new  ResponseEntity<>(res,HttpStatus.CREATED);
}
@GetMapping("/warehouses")
public ResponseEntity<ResponseStructure<List<Warehouse>>> getAllWarehouse(){
	ResponseStructure<List<Warehouse>> res=warehouseService.getAllWareHouses() ;
	return new  ResponseEntity<>(res,HttpStatus.OK);
}
@GetMapping("/warehouses/{id}")
public ResponseEntity<ResponseStructure<Warehouse>> getWarehouseById(@PathVariable Integer id){
	ResponseStructure<Warehouse> res=warehouseService.getWareHouseById(id) ;
	return new  ResponseEntity<>(res,HttpStatus.OK);
}
@GetMapping("/warehouses/location/{location}")
public ResponseEntity<ResponseStructure<List<Warehouse>>> getWarehousesByLocation(@PathVariable String location){
	ResponseStructure<List<Warehouse>> res=warehouseService.getWarehousesByLocation(location) ;
	return new  ResponseEntity<>(res,HttpStatus.OK);
}
@PutMapping("/warehouses/{id}")
public ResponseEntity<ResponseStructure<Warehouse>> updateWarehouse(
        @PathVariable Integer id,
        @RequestBody Warehouse warehouse){
	ResponseStructure<Warehouse> res =
	        warehouseService.updateWareHouse(id, warehouse);

	return new ResponseEntity<>(res, HttpStatus.OK);	
}
@DeleteMapping("/warehouses/{id}")
public ResponseEntity<ResponseStructure<String>> deleteWarehouse(@PathVariable Integer id){
	ResponseStructure<String> res=warehouseService.deleteWarehouse(id) ;
	return new  ResponseEntity<>(res,HttpStatus.OK);
}
@GetMapping("/warehouses/sort/capacity")
public ResponseEntity<ResponseStructure<List<Warehouse>>> sortByCapacity(){
	ResponseStructure<List<Warehouse>> res=warehouseService.sortByCapacity() ;
	return new  ResponseEntity<>(res,HttpStatus.OK);
}


}
