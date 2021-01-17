package com.tdeheurles.aerontest.mediadriver;

import io.aeron.driver.MediaDriver;
import static java.lang.System.exit;

public class Start {
    public static void main(String[] args){
        System.out.println("--- Start Media Driver with: "+String.join("", args)+" --- ");
        if (args.length != 1) {
            System.err.println("Please provide instance name as arg0");
            exit(1);
        }

        var aeronDir = "/dev/shm/" + args[0];
        System.out.println("aeronDir: " + aeronDir);
        final MediaDriver.Context driverContext = new MediaDriver.Context().aeronDirectoryName(aeronDir);
        driverContext.printConfigurationOnStart();
        MediaDriver.launch(driverContext);
    }
}
