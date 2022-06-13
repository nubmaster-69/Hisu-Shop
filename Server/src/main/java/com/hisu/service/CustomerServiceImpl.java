package com.hisu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hisu.dao.ICustomerDAO;
import com.hisu.entity.Customer;

@Service
public class CustomerServiceImpl implements ICustomerService {
	
	@Autowired
	private ICustomerDAO dao;
	
	@Override
	public List<Customer> getCustomers() {
		return dao.getCustomers();
	}

	@Override
	public Customer getCustomerById(int id) {
		return dao.getCustomerById(id);
	}

	@Override
	public void saveOrUpdateCustomer(Customer customer) {
		dao.saveOrUpdateCustomer(customer);
	}

	@Override
	public void deleteCustomer(int customerId) {
		dao.deleteCustomer(dao.getCustomerById(customerId));
	}
}