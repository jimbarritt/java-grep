package org.ixcode.grep.scan;

import java.io.*;

public class SearchDirectory {
    private final File delegate;

    public SearchDirectory(String directoryPath) {
        this.delegate = new File(directoryPath);        
    }

    public File asFile() {
        return delegate;
    }
}
