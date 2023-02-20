package com.github.artfly.ngit.model;

import org.jetbrains.annotations.NotNull;

import java.nio.file.Path;
import java.util.Objects;

public final class GitBlob {

    private final GitObjHeader header;
    private final Path content;

    private GitBlob(@NotNull GitObjHeader header, @NotNull Path content) {
        this.header = header;
        this.content = content;
    }

    public static @NotNull GitBlob create(@NotNull Path content, long size) {
        GitObjHeader header = new GitObjHeader("blob", size);
        return new GitBlob(header, content);
    }

    public @NotNull GitObjHeader header() {
        return header;
    }

    public @NotNull Path content() {
        return content;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (GitBlob) obj;
        return Objects.equals(this.header, that.header) &&
                Objects.equals(this.content, that.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(header, content);
    }

    @Override
    public String toString() {
        return "com.github.artfly.model.GitBlob[" +
                "header=" + header + ", " +
                "content=" + content + ']';
    }
}
