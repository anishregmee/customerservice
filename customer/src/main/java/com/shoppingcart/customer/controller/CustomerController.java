package com.shoppingcart.customer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.shoppingcart.customer.model.Customer;
import com.shoppingcart.customer.repository.CustomerRepository;

import jakarta.websocket.server.PathParam;

@RestController
public class CustomerController {
	
	
	@Autowired
	
	private CustomerRepository customerRepository;
	
	@GetMapping("/test")
	public String getMessage() {
		
		return "Welcome to springboot Application";
	}

	@GetMapping("/showMessage")
	public String getMyMessage() {

		return "Welcome to springboot JPA Application";
	}

	@GetMapping("/showAllCustomers")
	
	List<Customer>  showAllCustomers(){
		
		return  customerRepository.findAll();
	}
	
	@PostMapping("/addCustomer")  
	private Customer saveCustomer(@RequestBody Customer customer)   
	{  
		return customerRepository.save(customer);
	
	} 
	
	@PostMapping("/addCustomers")  
	private List<Customer> saveCustomers(@RequestBody List<Customer> customer)   
	{  
		return customerRepository.saveAll(customer);
	
	} 
	
	@GetMapping("/showCustomer/{id}")
	private ResponseEntity<?> getCustomerById(@PathVariable int id) {
		
		
		try {
		Customer customer = customerRepository.findById(id).get();	
	
		return ResponseEntity.ok(customer);		
		}
		catch(Exception e) {	
			return ResponseEntity.ok("No Record Found with "+ id);
		}
			
		
		//return customerRepository.findById(id).orElse(null);
	}
	
	@GetMapping("/showCustomerByName/{name}")
	private List<Customer> getCustomerByName(@PathVariable String name) {
		return customerRepository.findByCustomerName(name);
	}
	
	@DeleteMapping("/deleteCustomer/{id}")
	private String deleteCustomer(@PathVariable int id) {
		 customerRepository.deleteById(id);
		 return "succesfully deleted customer with id: " +id;
	}
	
	@PutMapping("/updateCustomer")
	private ResponseEntity<?> updateCustomer(@RequestBody Customer customer) {		

		try {
			Customer existingCustomer = customerRepository.findById(customer.getCustomrId()).get();
			
			existingCustomer.setCustomerName(customer.getCustomerName());
			existingCustomer.setCustomerAddress(customer.getCustomerAddress());
			existingCustomer.setContactno(customer.getContactno());
			 customerRepository.save(existingCustomer);
			  return ResponseEntity.ok("Record Updated Successfully.. ");
			}
		catch(Exception e) {	
			return ResponseEntity.ok("No Record Found with.... "+ customer.getCustomrId());
		}
						
	}
		
}
