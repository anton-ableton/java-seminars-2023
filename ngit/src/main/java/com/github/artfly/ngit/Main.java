package com.github.artfly.ngit;

import com.github.artfly.ngit.exception.GitCommandException;
import com.github.artfly.ngit.exception.GitIOException;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public class Main {

    public static void main(String @NotNull [] args) {
        if (args.length < 1) {
            System.err.println(usage());
            return;
        }

        String cmdName = args[0];

        Command command = Command.create(cmdName);
        if (command == null) {
            System.err.println("No such command: " + cmdName);
            System.err.println(usage());
            return;
        }

        String[] cmdArgs = Arrays.copyOfRange(args, 1, args.length);
        try {
            command.apply(cmdArgs);
        } catch (GitCommandException e) {
            System.err.println(e.getMessage());
            System.err.println(usage());
        } catch (GitIOException e) {
            System.err.println(e.getMessage());
        }
    }

    // TODO: update usage
    @Contract(pure = true)
    private static @NotNull String usage() {
        return """
                ngit pack filename
                ngit unpack filename
                """;
    }
}
