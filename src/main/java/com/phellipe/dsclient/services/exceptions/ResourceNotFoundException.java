package com.phellipe.dsclient.services.exceptions;

public class ResourceNotFoundException extends RuntimeException{
    private static final long serialVersionUID = 5085654092150955870L;

    public ResourceNotFoundException(String message) {
        super(message);
    }

}
