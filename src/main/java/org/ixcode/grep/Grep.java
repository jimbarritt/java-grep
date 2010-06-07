package org.ixcode.grep;

import org.ixcode.grep.match.*;
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

    public static List<MatchedFile> search(String rootDirectoryPath, String filenamePattern) {
        Grep grep = new Grep(new FileScanner(new SearchDirectory(rootDirectoryPath), new PatternBasedFilenameFilter(new FilenamePattern(filenamePattern))));
        return grep.searchFor(new GrepSearch(new CharSequenceMatcher(filenamePattern)));
    }
}
