package com.github.artfly.ngit;

import com.github.artfly.ngit.model.GitBlob;
import org.jetbrains.annotations.NotNull;
import org.junit.Test;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.Assert.assertEquals;

public class GitUtilsTest {

    private static final String TEST_PREFIX = "GitUtilsTest_";

    @Test
    public void empty() {
        byte[] content = new byte[0];
        GitBlob blob = create(content);
        Path actualGitPath = GitUtils.gitPath(blob);
        Path expectedGitPath = Path.of("bb", "6ca78b66403a67c6281df142de5ef472186283");
        assertEquals(expectedGitPath, actualGitPath);
    }

    @Test
    public void oneChar() {
        byte[] content = "a".getBytes(StandardCharsets.US_ASCII);
        GitBlob blob = create(content);
        Path actualGitPath = GitUtils.gitPath(blob);
        Path expectedGitPath = Path.of("2e", "65efe2a145dda7ee51d1741299f848e5bf752e");
        assertEquals(expectedGitPath, actualGitPath);
    }

    private static @NotNull GitBlob create(byte @NotNull [] content) {
        try {
            Path tmpFilePath = Files.createTempFile(TEST_PREFIX, null);
            Files.write(tmpFilePath, content);
            return GitBlob.create(tmpFilePath, content.length);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
