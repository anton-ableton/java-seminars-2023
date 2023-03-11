package com.github.artfly.jdu.model;

import java.nio.file.Path;

public record DuRegularFile(Path path) implements DuFile {}