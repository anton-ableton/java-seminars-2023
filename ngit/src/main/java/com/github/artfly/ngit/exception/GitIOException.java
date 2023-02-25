package com.github.artfly.ngit.exception;

import java.io.IOException;

public class GitIOException extends GitException {

    public GitIOException(IOException e) {
        super(e);
    }

}
