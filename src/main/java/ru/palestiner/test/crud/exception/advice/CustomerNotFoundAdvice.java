package ru.palestiner.test.crud.exception.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.palestiner.test.crud.exception.CustomerNotFoundException;

@ControllerAdvice
public class CustomerNotFoundAdvice {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(CustomerNotFoundException.class)
    String customerNotFoundHandler(CustomerNotFoundException e) {
        return e.getMessage();
    }
}
