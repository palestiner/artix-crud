package ru.palestiner.test.crud.service;

import ru.palestiner.test.crud.entity.Customer;

import java.util.List;

public interface CustomerService {
    List<Customer> findAll();

    void delete(Customer customer);

    Customer save(Customer customer);

    Customer replace(Customer customer, Long id);

    Customer findById(Long id);
}
