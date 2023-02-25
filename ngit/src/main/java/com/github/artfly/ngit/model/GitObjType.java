package com.github.artfly.ngit.model;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;

public enum GitObjType {
    BLOB("blob");

    private final String typeName;

    GitObjType(@NotNull String typeName) {
        this.typeName = typeName;
    }

    public static @Nullable GitObjType create(@NotNull String typeName) {
        return Arrays.stream(GitObjType.values())
                .filter(t -> typeName.equals(t.typeName))
                .findFirst().orElse(null);
    }

    public String typeName() {
        return typeName;
    }
}
