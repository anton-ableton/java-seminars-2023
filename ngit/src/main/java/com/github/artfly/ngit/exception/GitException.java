package com.github.artfly.ngit.exception;

public abstract class GitException extends RuntimeException {

    public GitException(String message) {
        super(message);
    }

    public GitException(Throwable t) {
        super(t);
    }
}
