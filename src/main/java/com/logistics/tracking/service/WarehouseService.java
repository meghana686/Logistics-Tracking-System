package com.logistics.tracking.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.logistics.tracking.dto.ResponseStructure;
import com.logistics.tracking.entity.Warehouse;
import com.logistics.tracking.exception.IdNotFoundException;
import com.logistics.tracking.exception.NoRecordAvailableException;
import com.logistics.tracking.repository.WarehouseRepository;

@Service
public class WarehouseService {

    @Autowired
    private WarehouseRepository warehouseRepository;

    public ResponseStructure<Warehouse> saveWarehouse(Warehouse warehouse) {
        if (warehouse.getName() == null || warehouse.getName().isBlank()) {
            throw new IllegalArgumentException("Warehouse name is required");
        }
        if (warehouse.getContactNumber() == null
                || warehouse.getContactNumber().isBlank()) {
            throw new IllegalArgumentException("Warehouse contact number is required");
        }

        if (!warehouse.getContactNumber().matches("[0-9]{10}")) {
            throw new IllegalArgumentException(
                    "Contact number should contain exactly 10 digits");
        }

        if (warehouseRepository.existsByContactNumber(warehouse.getContactNumber())) {
            throw new IllegalArgumentException("Contact number already exists");
        }

        
        Warehouse savedWarehouse = warehouseRepository.save(warehouse);
        ResponseStructure<Warehouse> response = new ResponseStructure<>();
        response.setData(savedWarehouse);
        response.setMessage("Warehouse successfully added");
        response.setStatusCode(HttpStatus.CREATED.value());

        return response;
    }
    public ResponseStructure<List<Warehouse>> getAllWareHouses() {
    	List<Warehouse> warehouses= warehouseRepository.findAll();
    	if(warehouses.isEmpty()) {
    		throw new NoRecordAvailableException("No records available in the warehouse");
    	}
    	 ResponseStructure<List<Warehouse>> response = new ResponseStructure<>();
         response.setData(warehouses);
         response.setMessage("Warehouses retrieved successfully");
         response.setStatusCode(HttpStatus.OK.value());

         return response;
    	
    }
    public ResponseStructure<Warehouse> getWareHouseById(Integer id) {
    	Optional<Warehouse> warehouse= warehouseRepository.findById(id);
    	if(warehouse.isEmpty()) {
    		throw new IdNotFoundException("warehouse is not found with the given id :"+id);
    	}
    	 ResponseStructure<Warehouse> response = new ResponseStructure<>();
         response.setData(warehouse.get());
         response.setMessage("Warehouse retrieved successfully");
         response.setStatusCode(HttpStatus.OK.value());

         return response;
    	
    }
    public ResponseStructure<List<Warehouse>> getWarehousesByLocation(String location){
    	List<Warehouse> warehouses= warehouseRepository.findByLocation(location);
    	if(warehouses.isEmpty()) {
    		throw new NoRecordAvailableException("No records available in the warehouse");
    	}
    	 ResponseStructure<List<Warehouse>> response = new ResponseStructure<>();
         response.setData(warehouses);
         response.setMessage("Warehouses retrieved successfully");
         response.setStatusCode(HttpStatus.OK.value());

         return response;	
    }
    public ResponseStructure<Warehouse> updateWareHouse(Integer id,Warehouse warehouse) {
    	Optional<Warehouse> newWarehouse= warehouseRepository.findById(id);
    	if(newWarehouse.isEmpty()) {
    		throw new IdNotFoundException("warehouse is not found with the given id :"+id);
    	}
    	 Warehouse wareHouse1=newWarehouse.get();
    	 wareHouse1.setName(warehouse.getName());
    	 wareHouse1.setCapacity(warehouse.getCapacity());
    	 wareHouse1.setContactNumber(warehouse.getContactNumber());
    	 wareHouse1.setLocation(warehouse.getLocation());
    	 Warehouse updatedWarehouse = warehouseRepository.save(wareHouse1);
    	 ResponseStructure<Warehouse> response = new ResponseStructure<>();
         response.setData(updatedWarehouse);
         response.setMessage("Warehouse updated successfully");
         response.setStatusCode(HttpStatus.OK.value());

         return response;
    	
    }
    public ResponseStructure<String> deleteWarehouse(Integer id){
    	Optional<Warehouse> warehouse= warehouseRepository.findById(id);
    	if(warehouse.isEmpty()) {
    		throw new IdNotFoundException("warehouse is not found with the given id :"+id);
    	}
    	 warehouseRepository.delete(warehouse.get());   
    	 ResponseStructure<String> response = new ResponseStructure<>();
         response.setData("Warehouse deleted sucessfully");
         response.setMessage("Warehouse deleted  successfully");
         response.setStatusCode(HttpStatus.OK.value());

         return response;	
    }
    public ResponseStructure<List<Warehouse>> sortByCapacity(){
    	List<Warehouse> warehouses= warehouseRepository.findAllByOrderByCapacityAsc();
    	if(warehouses.isEmpty()) {
    		throw new NoRecordAvailableException("No records available in the warehouse");
    	}
    	 ResponseStructure<List<Warehouse>> response = new ResponseStructure<>();
         response.setData(warehouses);
         response.setMessage("Warehouses retrieved successfully");
         response.setStatusCode(HttpStatus.OK.value());

         return response;
    	
    }
}