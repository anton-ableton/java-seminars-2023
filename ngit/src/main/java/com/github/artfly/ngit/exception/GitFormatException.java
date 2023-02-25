package com.github.artfly.ngit.exception;

import org.jetbrains.annotations.NotNull;

public class GitFormatException extends GitException {
    public GitFormatException(@NotNull String message) {
        super(message);
    }
}