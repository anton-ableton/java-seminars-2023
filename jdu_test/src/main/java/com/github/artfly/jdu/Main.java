package com.github.artfly.jdu;

import java.nio.file.Files;
import java.nio.file.Path;

public class Main {

    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Expected root path to directory or file");
            return;
        }
        Path rootPath = Path.of(args[0]);
        if (!Files.exists(rootPath)) {
            System.err.println("Expected root path to directory or file");
        }
        DuTreeBuilder.build(rootPath);
    }
}
