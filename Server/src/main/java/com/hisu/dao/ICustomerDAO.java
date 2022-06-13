package com.hisu.dao;

import java.util.List;

import com.hisu.entity.Customer;

public interface ICustomerDAO {
	List<Customer> getCustomers();
	Customer getCustomerById(int id);
	void saveOrUpdateCustomer(Customer customer);
	void deleteCustomer(Customer customer);
}