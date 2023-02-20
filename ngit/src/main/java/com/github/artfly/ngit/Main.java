package com.github.artfly.ngit;

import com.github.artfly.ngit.model.GitBlob;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {

    public static void main(String @NotNull [] args) {
        if (args.length != 1) {
            System.err.println(usage());
            return;
        }

        String filename = args[0];
        Path inPath = Path.of(filename);
        if (!Files.isRegularFile(inPath)) {
            System.err.println("Cannot create git object file for non-regular file");
            return;
        }

        try {
            GitBlob blob = create(inPath);
            Path outPath = GitUtils.gitPath(blob);
            GitUtils.write(outPath, blob);
        } catch (GitIOException e) {
            System.err.println(e.getMessage());
        }
    }

    private static @NotNull GitBlob create(@NotNull Path content) {
        try {
            long size = Files.size(content);
            return GitBlob.create(content, size);
        } catch (IOException e) {
            throw new GitIOException(e);
        }
    }

    @Contract(pure = true)
    private static @NotNull String usage() {
        return """
                ngit filename
                """;
    }
}
