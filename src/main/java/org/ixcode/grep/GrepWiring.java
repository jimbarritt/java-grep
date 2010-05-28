package org.ixcode.grep;

import org.ixcode.grep.scan.*;

public class GrepWiring {
    public FileScanner createFileScanner(String file, String filenamePattern) {
        return null;
    }

    public FileGrepScanningAction createFileGrepScanningAction(String searchExpression, Grep.SearchOption... searchOptions) {
        return null;
    }
}
