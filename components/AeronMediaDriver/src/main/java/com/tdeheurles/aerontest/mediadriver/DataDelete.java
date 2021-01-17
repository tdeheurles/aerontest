package com.tdeheurles.aerontest.mediadriver;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;

import static java.lang.System.exit;

public class DataDelete {
    public static void main(String[] args) throws Exception {
        System.out.println("--- Delete Media Driver Data with: "+String.join("", args)+" --- ");
        if (args.length != 1) {
            System.err.println("Please provide instance name as arg0");
            exit(1);
        }

        // TODO: Configure aeronDir
        var aeronDirPath = Paths.get("/dev/shm/" + args[0]);
        var aeronDir = new File(String.valueOf(aeronDirPath));
        if (! aeronDir.exists()) {
            System.err.println("AeronDir "+aeronDirPath+" doesn't exist");
            exit(0);
        }

        Files.walk(aeronDirPath)
                .sorted(Comparator.reverseOrder())
                .map(Path::toFile)
                .forEach(File::delete);

        if (! aeronDir.exists()) {
            System.err.println("AeronDir "+aeronDirPath+" have been deleted");
            exit(0);
        }

        System.err.println("Command failed to delete AeronDir "+aeronDirPath);
        exit(1);
    }
}
