package ru.palestiner.test.crud.exception;

public class CustomerNotFoundException extends RuntimeException {
    public CustomerNotFoundException(Long id) {
        super("Could not find customer by id = " + id);
    }
}
