package com.github.artfly.jdu;

import com.github.artfly.jdu.model.DuDirectory;
import com.github.artfly.jdu.model.DuFile;
import com.github.artfly.jdu.model.DuRegularFile;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

public class DuTreeBuilder {

    public static DuFile build(Path path) {
        if (Files.isRegularFile(path)) {
            return new DuRegularFile(path);
        }

        try (Stream<Path> files = Files.list(path)) {
            List<DuFile> children = files.map(DuTreeBuilder::build).toList();
            return new DuDirectory(path, children);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

}
