package org.ixcode.grep;

import org.ixcode.grep.scan.*;

import java.util.*;

public class Grep {
    private final FileScanner fileScanner;

    public Grep(FileScanner fileScanner) {
        this.fileScanner = fileScanner;
    }

    public List<MatchedFile> searchFor(GrepSearch grepSearch) {
        fileScanner.scan(grepSearch.fileScanningAction());
        return grepSearch.matchedFiles();
    }

}
