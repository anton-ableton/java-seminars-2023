package com.github.artfly.jdu.model;

import java.nio.file.Path;
import java.util.List;

public record DuDirectory(Path path, List<DuFile> children) implements DuFile {}
