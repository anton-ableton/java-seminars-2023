package com.github.artfly.ngit;

import java.io.IOException;

public class GitIOException extends RuntimeException {

    public GitIOException(IOException e) {
        super(e);
    }

}
