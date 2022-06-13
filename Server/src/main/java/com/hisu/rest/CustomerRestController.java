package com.hisu.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hisu.entity.Customer;
import com.hisu.service.ICustomerService;

@RestController
@RequestMapping("/api/customers")
public class CustomerRestController {
	
	@Autowired
	private ICustomerService customerService;
	
	@GetMapping
	private List<Customer> getCustomers() {
		return customerService.getCustomers();
	}
	
	@GetMapping("/{id}")
	private Customer getCustomerById(@PathVariable int id) {
		return customerService.getCustomerById(id);
	}
	
	@PostMapping
	private Customer addNewCustomer(@RequestBody Customer customer) {
		return customer;
	}
	
	@PutMapping
	private Customer updateCustomer(@RequestBody Customer customer) {
		return customer;
	}
	
	@DeleteMapping("/{id}")
	private void deleteCustomer(@PathVariable int id) {
		customerService.deleteCustomer(id);
	}
}