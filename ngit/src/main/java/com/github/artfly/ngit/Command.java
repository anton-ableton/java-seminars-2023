package com.github.artfly.ngit;

import com.github.artfly.ngit.exception.GitCommandException;
import com.github.artfly.ngit.exception.GitIOException;
import com.github.artfly.ngit.model.GitBlob;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public interface Command {

    void apply(String[] cmdArgs);

    String getName();

    Command[] COMMANDS = {
            new Pack(),
            new Unpack()
    };

    static @Nullable Command create(@NotNull String name) {
        for (Command command : COMMANDS) {
            if (name.equals(command.getName())) {
                return command;
            }
        }
        return null;
    }
}

class Pack implements Command {

    @Override
    public void apply(String[] cmdArgs) {
        if (cmdArgs.length > 1) {
            throw error("too many arguments");
        }
        if (cmdArgs.length < 1) {
            throw error("not enough arguments");
        }
        Path inPath = Path.of(cmdArgs[0]);
        if (!Files.isRegularFile(inPath)) {
            throw error("Cannot create git object file for non-regular file");
        }

        GitBlob blob = create(inPath);
        Path outPath = GitUtils.gitPath(blob);
        GitUtils.write(outPath, blob);
    }

    private static @NotNull GitBlob create(@NotNull Path content) {
        try {
            long size = Files.size(content);
            return GitBlob.create(content, size);
        } catch (IOException e) {
            throw new GitIOException(e);
        }
    }

    private GitCommandException error(String message) {
        return new GitCommandException(message, getName());
    }

    @Override
    public String getName() {
        return "pack";
    }
}

class Unpack implements Command {

    @Override
    public void apply(String[] cmdArgs) {
        Path inPath = Path.of(cmdArgs[0]);
        if (!Files.isRegularFile(inPath)) {
            throw error("Cannot create git object file for non-regular file");
        }
        throw new UnsupportedOperationException("no unpack yet");
    }

    private GitCommandException error(String message) {
        return new GitCommandException(message, getName());
    }

    @Override
    public String getName() {
        return "unpack";
    }
}