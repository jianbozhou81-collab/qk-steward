package com.qk.exception;

public class BuisnessException extends RuntimeException {
    public BuisnessException() {
    }

    public BuisnessException(String message) {
      super(message);
    }
}
