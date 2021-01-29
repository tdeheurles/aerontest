package com.tdeheurles.aerontest.cluster;

import org.agrona.ErrorHandler;

public class BasicErrorHandler {
    static ErrorHandler errorHandler(final String context)
    {
        return
                (Throwable throwable) ->
                {
                    System.err.println(context);
                    throwable.printStackTrace(System.err);
                };
    }
}
