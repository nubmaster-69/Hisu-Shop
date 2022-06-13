package com.hisu.service;

import java.util.List;

import com.hisu.entity.Customer;

public interface ICustomerService {
	List<Customer> getCustomers();
	Customer getCustomerById(int id);
	void saveOrUpdateCustomer(Customer customer);
	void deleteCustomer(int customerID);
}