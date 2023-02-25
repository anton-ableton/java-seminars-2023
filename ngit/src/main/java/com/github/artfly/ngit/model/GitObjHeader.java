package com.github.artfly.ngit.model;

import org.jetbrains.annotations.NotNull;

import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;

public record GitObjHeader(String type, long size) {

    public byte @NotNull [] bytes() {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        bytes.writeBytes(type.getBytes(StandardCharsets.US_ASCII));
        bytes.write(' ');
        bytes.writeBytes(String.valueOf(size).getBytes(StandardCharsets.US_ASCII));
        return bytes.toByteArray();
    }
}