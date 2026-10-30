package com.logistics.tracking.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.logistics.tracking.dto.ResponseStructure;
import com.logistics.tracking.entity.Customer;
import com.logistics.tracking.exception.IdNotFoundException;
import com.logistics.tracking.exception.NoRecordAvailableException;
import com.logistics.tracking.repository.CustomerRepository;

@Service
public class CustomerService {
	@Autowired
	private CustomerRepository customerRepository ;
	public ResponseStructure<Customer> saveCustomer(Customer customer){
		if(customer.getEmail()==null || customer.getEmail().isBlank()){
			throw new IllegalArgumentException("Email is required");
		}
		if(customerRepository.existsByEmail(customer.getEmail())){
			throw new IllegalArgumentException("Email should be unique");
		}
		if(customer.getContact()==null || customer.getContact().isBlank()){
			throw new IllegalArgumentException("contact is required");
		}
		if(customerRepository.existsByContact(customer.getContact())){
			throw new IllegalArgumentException("Contact should be unique");
		}
		if(customer.getContact()==null||!customer.getContact().matches("[0-9]{10}")) {
			throw new IllegalArgumentException("Contact should be exactly 10 digits");
		}
		 Customer savedCustomer = customerRepository.save(customer);
	        ResponseStructure<Customer> res = new ResponseStructure<>();
	        res.setData(savedCustomer);
	        res.setMessage("Customer record saved successfully");
	        res.setStatusCode(HttpStatus.CREATED.value());
	        return res;
}
	public ResponseStructure<List<Customer>> getAllCustomers(){
		 List<Customer> customers=customerRepository.findAll();
		 if(customers.isEmpty()) {
			 throw new NoRecordAvailableException("no records available in customer");
		 }
		 ResponseStructure<List<Customer>> res = new ResponseStructure<>();
	        res.setData(customers);
	        res.setMessage("Customers record retrieved successfully");
	        res.setStatusCode(HttpStatus.OK.value());
	        return res;
		
	}
	public ResponseStructure<Customer> getCustomerById(Integer id){
		Optional<Customer> customer=customerRepository.findById(id);
		if(customer.isEmpty()) {
			throw new IdNotFoundException("customer record is not there for given id "+id);
		}
		ResponseStructure<Customer> res = new ResponseStructure<>();
        res.setData(customer.get());
        res.setMessage("Customer record retrieved successfully");
        res.setStatusCode(HttpStatus.OK.value());
        return res;
	}
	public ResponseStructure<Customer> getCustomerByEmail(String email){
		Optional<Customer> customer=customerRepository.findByEmail(email);
		if(customer.isEmpty()) {
			throw new IdNotFoundException("customer record is not there for given email "+email);
		}
		ResponseStructure<Customer> res = new ResponseStructure<>();
        res.setData(customer.get());
        res.setMessage("Customer record retrieved successfully");
        res.setStatusCode(HttpStatus.OK.value());
        return res;
	}
	public ResponseStructure<Customer> getCustomerByContact(String contact){
		Optional<Customer> customer=customerRepository.findByContact(contact);
		if(customer.isEmpty()) {
			throw new IdNotFoundException("customer record is not there for given contact "+contact);
		}
		ResponseStructure<Customer> res = new ResponseStructure<>();
        res.setData(customer.get());
        res.setMessage("Customer record retrieved successfully");
        res.setStatusCode(HttpStatus.OK.value());
        return res;
	}
	public ResponseStructure<Customer> upadateCustomer(Integer id, Customer customer){
		Optional<Customer> newCustomer=customerRepository.findById(id);
		if(newCustomer.isEmpty()) {
			throw new IdNotFoundException("customer record is not there for given id "+id);
		}
		Customer customer1=newCustomer.get();
		customer1.setName(customer.getName());
		customer1.setEmail(customer.getEmail());
		customer1.setContact(customer.getContact());
		customer1.setAddress(customer.getAddress());
		Customer custo=customerRepository.save(customer1);
	
		
		
		ResponseStructure<Customer> res = new ResponseStructure<>();
        res.setData(custo);
        res.setMessage("Customer record Updated successfully");
        res.setStatusCode(HttpStatus.OK.value());
        return res;
	}
	public ResponseStructure<String> deleteCustomer(Integer id) {
	    Optional<Customer> newCustomer = customerRepository.findById(id);

	    if (newCustomer.isEmpty()) {
	        throw new IdNotFoundException(
	            "Customer record is not available for the given ID: " + id
	        );
	    }

	    customerRepository.delete(newCustomer.get());

	    ResponseStructure<String> res = new ResponseStructure<>();
	    res.setData("Customer deleted successfully");
	    res.setMessage("Customer record deleted successfully");
	    res.setStatusCode(HttpStatus.OK.value());

	    return res;
	}
	public ResponseStructure<Page<Customer>> getCustomerByPagenationAndSorting(
	        int pageNumber, int pageSize, String fieldName) {

	    Page<Customer> page = customerRepository.findAll(
	        PageRequest.of(pageNumber, pageSize, Sort.by(fieldName).descending())
	    );

	    if (page.isEmpty()) {
	        throw new NoRecordAvailableException("No records found");
	    }

	    ResponseStructure<Page<Customer>> res = new ResponseStructure<>();
	    res.setData(page);
	    res.setMessage("Customer records retrieved successfully");
	    res.setStatusCode(HttpStatus.OK.value());

	    return res;
	}
}
