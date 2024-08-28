package com.example.bookstoreapi.service;

import com.example.bookstoreapi.dto.CustomerDTO;
import com.example.bookstoreapi.model.Customer;

import java.util.List;

public interface CustomerService {
    Customer createCustomer(CustomerDTO customerDTO);
    Customer getCustomerById(Long id);
    Customer updateCustomer(Long id, CustomerDTO customerDTO);
    void deleteCustomer(Long id);
    List<Customer> getAllCustomers();
}