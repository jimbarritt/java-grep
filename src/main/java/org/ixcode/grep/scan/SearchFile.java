package org.ixcode.grep.scan;

import java.io.*;

public class SearchFile {
    private final File delegate;

    public SearchFile(File delegate) {
        this.delegate = delegate;
    }

    public String toString() {
        return delegate.getAbsolutePath();
    }
}
