package com.github.artfly.ngit.model;

import org.jetbrains.annotations.NotNull;

import java.nio.file.Path;
import java.util.Objects;

public final class GitBlob {

    private final GitObjHeader header;
    private final Path original;

    private GitBlob(@NotNull GitObjHeader header, @NotNull Path original) {
        this.header = header;
        this.original = original;
    }

    public static @NotNull GitBlob create(@NotNull Path content, long size) {
        GitObjHeader header = new GitObjHeader(GitObjType.BLOB, size);
        return new GitBlob(header, content);
    }

    public @NotNull GitObjHeader header() {
        return header;
    }

    public @NotNull Path original() {
        return original;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (GitBlob) obj;
        return Objects.equals(this.header, that.header) &&
                Objects.equals(this.original, that.original);
    }

    @Override
    public int hashCode() {
        return Objects.hash(header, original);
    }

    @Override
    public String toString() {
        return "GitBlob[" +
                "header=" + header + ", " +
                "content=" + original + ']';
    }
}
