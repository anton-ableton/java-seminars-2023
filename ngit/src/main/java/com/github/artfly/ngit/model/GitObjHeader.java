package com.github.artfly.ngit.model;

import com.github.artfly.ngit.exception.GitFormatException;
import org.jetbrains.annotations.NotNull;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public record GitObjHeader(GitObjType type, long size) {

    public byte @NotNull [] bytes() {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        bytes.writeBytes(type.typeName().getBytes(StandardCharsets.US_ASCII));
        bytes.write(' ');
        bytes.writeBytes(String.valueOf(size).getBytes(StandardCharsets.US_ASCII));
        bytes.write(0);
        return bytes.toByteArray();
    }

    @SuppressWarnings("UnusedReturnValue")
    public static @NotNull GitObjHeader fromBytes(@NotNull InputStream stream) throws IOException {
        GitObjType type = readType(stream);
        long size = readSize(stream);
        return new GitObjHeader(type, size);
    }

    private static @NotNull GitObjType readType(@NotNull InputStream stream) throws IOException {
        ByteArrayOutputStream type = new ByteArrayOutputStream();
        int c;
        while ((c = stream.read()) != ' ') {
            if (c == -1) {
                throw new GitFormatException("incorrect git object header: eof while reading type");
            }
            type.write(c);
        }
        return parseType(type.toString(StandardCharsets.US_ASCII));
    }

    private static @NotNull GitObjType parseType(@NotNull String typeName) {
        GitObjType gitObjType = GitObjType.create(typeName);
        if (gitObjType == null) {
            throw new GitFormatException("incorrect git object header: unknown obj type");
        }
        return gitObjType;
    }

    private static long readSize(@NotNull InputStream stream) throws IOException {
        ByteArrayOutputStream size = new ByteArrayOutputStream();
        int c;
        while ((c = stream.read()) != 0) {
            if (c == -1) {
                throw new GitFormatException("incorrect git object header: eof while reading size");
            }
            size.write(c);
        }
        return parseSize(size.toString(StandardCharsets.US_ASCII));
    }

    private static long parseSize(@NotNull String size) {
        try {
            return Long.parseLong(size);
        } catch (NumberFormatException e) {
            throw new GitFormatException("incorrect git object header: cannot parse size");
        }
    }
}