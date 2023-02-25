package com.github.artfly.ngit;

import com.github.artfly.ngit.model.GitBlob;
import org.jetbrains.annotations.NotNull;
import org.junit.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.Assert.assertEquals;

public class GitUtilsTest {

    private static final String TEST_PREFIX = "GitUtilsTest_";

    @Test
    public void packEmpty() {
        byte[] content = new byte[0];
        GitBlob blob = createBlobFromOriginalContent(content);
        Path actualGitPath = GitUtils.gitPath(blob);
        Path expectedGitPath = Path.of("a1", "724a4a34ac5d2ba83ab12ae06e9b1476e3f005");
        assertEquals(expectedGitPath, actualGitPath);
    }

    @Test
    public void packOneChar() {
        byte[] content = "a".getBytes(StandardCharsets.US_ASCII);
        GitBlob blob = createBlobFromOriginalContent(content);
        Path actualGitPath = GitUtils.gitPath(blob);
        Path expectedGitPath = Path.of("d5", "7f6f51c055d6cd5546d85772d144bef48096eb");
        assertEquals(expectedGitPath, actualGitPath);
    }

    private static @NotNull GitBlob createBlobFromOriginalContent(byte @NotNull [] content) {
        try {
            Path tmpFilePath = Files.createTempFile(TEST_PREFIX, null);
            Files.write(tmpFilePath, content);
            return GitBlob.create(tmpFilePath, content.length);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
