package com.kubapiecuch.springbootwithdatabase.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotAvailableException extends RuntimeException {
    public ResourceNotAvailableException(String message){
        super(message);
    }

}
