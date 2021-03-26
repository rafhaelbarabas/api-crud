package com.rafhael.barabas.crud.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

    private static final long serialVersionUID = -7296630117031881433L;

    public ResourceNotFoundException() {
        super("No records found");
    }

    public ResourceNotFoundException(String exception) {
        super(exception);
    }

}
