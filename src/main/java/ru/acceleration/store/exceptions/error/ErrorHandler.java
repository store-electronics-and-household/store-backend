package ru.acceleration.store.exceptions.error;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.acceleration.store.exceptions.IDException;
import ru.acceleration.store.exceptions.ValidateException;

@Slf4j
@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String error400(final ValidateException e) {
        log.info("400 {}", e.getMessage());
        return e.getMessage();

    }

    @ExceptionHandler
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public String error404(final IDException e) {
        log.info("404 {}", e.getMessage());
        return e.getMessage();
    }
}

