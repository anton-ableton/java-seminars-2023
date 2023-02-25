package com.github.artfly.ngit.exception;

import org.jetbrains.annotations.NotNull;

public abstract class GitException extends RuntimeException {

    public GitException(@NotNull String message) {
        super(message);
    }

    public GitException(@NotNull Throwable t) {
        super(t);
    }
}
