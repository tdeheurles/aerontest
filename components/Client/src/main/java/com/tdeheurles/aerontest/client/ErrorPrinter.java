package com.tdeheurles.aerontest.client;

import org.agrona.ErrorHandler;

public class ErrorPrinter implements ErrorHandler {
    @Override
    public void onError(Throwable throwable) {
        System.err.println(throwable.getMessage());
    }
}
