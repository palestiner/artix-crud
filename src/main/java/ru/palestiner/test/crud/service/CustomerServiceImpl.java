package ru.palestiner.test.crud.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.palestiner.test.crud.entity.Customer;
import ru.palestiner.test.crud.exception.CustomerNotFoundException;
import ru.palestiner.test.crud.repositories.CustomerRepo;

import java.util.List;


@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepo customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepo customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    public void delete(Customer customer) {
        customerRepository.delete(customer);
    }

    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    public Customer replace(Customer newCustomer, Long id) {
        return customerRepository
                .findById(id)
                .map(customer -> {
                    customer.setName(newCustomer.getName());
                    customer.setGender(newCustomer.getGender());
                    customer.setDayOfBirth(newCustomer.getDayOfBirth());
                    return customerRepository.save(customer);
                })
                .orElseGet(() -> {
                    newCustomer.setId(id);
                    return customerRepository.save(newCustomer);
                });
    }

    public Customer findById(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException(id));
    }
}
