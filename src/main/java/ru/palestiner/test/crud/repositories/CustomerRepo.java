package ru.palestiner.test.crud.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.palestiner.test.crud.entity.Customer;

public interface CustomerRepo extends JpaRepository<Customer, Long> {
}
