package com.github.artfly.ngit.exception;

public class GitCommandException extends GitException {

    public GitCommandException(String message, String cmdName) {
        super("git " + cmdName + ": " + message);
    }
}
