package com.shoppingcart.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shoppingcart.customer.model.Customer;
import java.util.List;


@Repository
public interface CustomerRepository   extends  JpaRepository<Customer, Integer>{

	List<Customer> findByCustomerName(String customerName);

}
