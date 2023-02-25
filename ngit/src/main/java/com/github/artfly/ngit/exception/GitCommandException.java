package com.github.artfly.ngit.exception;

import org.jetbrains.annotations.NotNull;

public class GitCommandException extends GitException {

    public GitCommandException(@NotNull String message, @NotNull String cmdName) {
        super("git " + cmdName + ": " + message);
    }
}
