package com.github.artfly.ngit.exception;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class GitIOException extends GitException {

    public GitIOException(@NotNull IOException e) {
        super(e);
    }

}
