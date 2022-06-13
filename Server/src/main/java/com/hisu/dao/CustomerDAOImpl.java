package com.hisu.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hisu.entity.Customer;

@Repository
public class CustomerDAOImpl implements ICustomerDAO {

	@Autowired
	private SessionFactory factory;
	
	@Override
	@Transactional
	public List<Customer> getCustomers() {
		return factory.getCurrentSession().createQuery("from Customer", Customer.class).getResultList();
	}

	@Override
	@Transactional
	public Customer getCustomerById(int id) {
		return factory.getCurrentSession().get(Customer.class, id);
	}

	@Override
	@Transactional
	public void saveOrUpdateCustomer(Customer customer) {
		factory.getCurrentSession().save(customer);
	}

	@Override
	@Transactional
	public void deleteCustomer(Customer customer) {
		factory.getCurrentSession().delete(customer);
	}
}