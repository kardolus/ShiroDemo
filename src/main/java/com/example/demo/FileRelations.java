package com.example.demo;

import java.nio.file.Path;

class FileRelations {
    private final Path file;
    private FileRelations(Path file) {
        this.file = file;
    }

    static FileRelations is(Path file) {
        return new FileRelations(file);
    }

    boolean descendantOf(Path potentialAncestor) {
        return isDescendantOf(potentialAncestor);
    }

    boolean theSameAs(Path otherFile) {
        return this.file.equals(otherFile);
    }

    private boolean isDescendantOf(Path potentialAncestor) {
        return file.startsWith(potentialAncestor);
    }
}
