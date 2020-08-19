package com.example.test.Entity;

public class AsyncRet<T> {

    private boolean isError;
    private int errorCode;
    private String message;
    private T obj;

    public AsyncRet(boolean _isError, int _errorCode, String _message, T _obj) {
        isError = _isError;
        errorCode = _errorCode;
        message = _message;
        obj = _obj;
    }

    public boolean isError() {
        return isError;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getMessage() {
        return message;
    }

    public T getObject() {
        return obj;
    }
}

