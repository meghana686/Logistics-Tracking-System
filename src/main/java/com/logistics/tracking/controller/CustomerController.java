package com.logistics.tracking.controller;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.logistics.tracking.dto.ResponseStructure;
import com.logistics.tracking.entity.Customer;
import com.logistics.tracking.service.CustomerService;

@RestController
public class CustomerController {
	@Autowired
    private CustomerService customerService;
    @PostMapping("/customers")
	public ResponseEntity<ResponseStructure<Customer>> saveCustomer(@RequestBody Customer customer){
		ResponseStructure<Customer> res=customerService.saveCustomer(customer);
		return new  ResponseEntity<>(res,HttpStatus.CREATED) ;
	}
    @GetMapping("/customers")
   	public ResponseEntity<ResponseStructure<List<Customer>>> getAllCustomers(){
   		ResponseStructure<List<Customer>> res=customerService.getAllCustomers();
   		return new  ResponseEntity<>(res,HttpStatus.OK) ;
   	}
    @GetMapping("/customers/{id}")
   	public ResponseEntity<ResponseStructure<Customer>> getCustomerById(@PathVariable Integer id){
   		ResponseStructure<Customer> res=customerService.getCustomerById(id);
   		return new  ResponseEntity<>(res,HttpStatus.OK) ;
   	}
    @GetMapping("/customers/{email}")
   	public ResponseEntity<ResponseStructure<Customer>> getCustomerByEmail(@PathVariable String email){
    	ResponseStructure<Customer> res=customerService.getCustomerByEmail(email);
   		return new  ResponseEntity<>(res,HttpStatus.OK) ;
    	
    }
    @GetMapping("/customers/contact/{contact}")
   	public ResponseEntity<ResponseStructure<Customer>> getCustomerByContact(@PathVariable String contact){
    	ResponseStructure<Customer> res=customerService.getCustomerByContact(contact);
   		return new  ResponseEntity<>(res,HttpStatus.OK) ;
    	
    }
    @PutMapping("/customers/{id}")
   	public ResponseEntity<ResponseStructure<Customer>> updateCustomer(@PathVariable Integer id, @RequestBody Customer customer){
    	ResponseStructure<Customer> res=customerService.upadateCustomer(id,customer);
   		return new  ResponseEntity<>(res,HttpStatus.OK) ;
    	
    }
    @DeleteMapping("/customers/{id}")
   	public ResponseEntity<ResponseStructure<String>> deleteCustomer(@PathVariable Integer id){
    	ResponseStructure<String> res=customerService.deleteCustomer(id);
   		return new  ResponseEntity<>(res,HttpStatus.OK) ;
    	
    }
    @GetMapping("/customers/pagination")
    public ResponseEntity<ResponseStructure<Page<Customer>>> getCustomerByPaginationAndSorting(
            @RequestParam int pageNumber,
            @RequestParam int pageSize,
            @RequestParam String fieldName) {
    	ResponseStructure<Page<Customer>> res=customerService.getCustomerByPagenationAndSorting(pageNumber,pageSize,fieldName);
   		return new  ResponseEntity<>(res,HttpStatus.OK);
    }
}
