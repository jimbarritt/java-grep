package org.ixcode.grep;

import org.ixcode.grep.scan.*;

import java.util.*;

public class Grep {
    private FileScanner fileScanner;
    private final GrepWiring grepWiring;


    public static enum SearchOption {
        CASE_INSENSITIVE
    }

    public Grep(String rootDirectory, String fileNamePattern, GrepWiring grepWiring) {
        this.grepWiring = grepWiring;
        fileScanner = grepWiring.createFileScanner(rootDirectory,fileNamePattern);
    }

    public List<MatchedFile> searchFor(String searchExpression, SearchOption... searchOptions) {
        FileGrepScanningAction fileScanningAction = grepWiring.createFileGrepScanningAction(searchExpression, searchOptions);

        fileScanner.scan(fileScanningAction);

        return fileScanningAction.getMatchedFiles();
    }

}
