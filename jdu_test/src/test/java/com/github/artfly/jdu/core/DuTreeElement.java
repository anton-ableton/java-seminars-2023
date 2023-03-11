package com.github.artfly.jdu.core;

import com.github.artfly.jdu.model.DuDirectory;
import com.github.artfly.jdu.model.DuFile;
import com.github.artfly.jdu.model.DuRegularFile;

import java.nio.file.FileSystem;
import java.nio.file.Path;
import java.util.List;

public record DuTreeElement(Type type, String path, List<DuTreeElement> children) {

    public static DuFile tree(FileSystem fs, DuTreeElement root) {
        return buildTree(root, fs.getPath("/"));
    }

    private static DuFile buildTree(DuTreeElement treeElement, Path parentPath) {
        Path currentPath = parentPath.resolve(treeElement.path);
        if (treeElement.type == Type.FILE) {
            return new DuRegularFile(currentPath);
        }
        List<DuFile> duChildren = treeElement.children.stream().map(c -> buildTree(c, currentPath)).toList();
        return new DuDirectory(currentPath, duChildren);
    }

    public static DuTreeElement dir(String name, DuTreeElement... files) {
        return new DuTreeElement(Type.DIR, name, List.of(files));
    }

    public static DuTreeElement file(String name) {
        return new DuTreeElement(Type.FILE, name, null);
    }

    private enum Type {
        DIR,
        FILE
    }
}
