package com.agniva.springdemo.dao;

import java.util.List;

import com.agniva.springdemo.entity.Customer;

public interface CustomerDAO {
	
	public List<Customer> getCustomers();

	public void saveCustomer(Customer theCustomer);

}
