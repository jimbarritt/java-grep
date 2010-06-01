package org.ixcode.grep.scan;

import org.apache.log4j.*;

import java.io.*;
import java.util.*;

public class FileScanner {
    private static final Logger log = Logger.getLogger(FileScanner.class);

    
    private final SearchDirectory rootDir;
    private final FilenamePattern filenamePattern;

    public FileScanner(SearchDirectory rootDir, FilenamePattern filenamePattern) {
        this.rootDir = rootDir;
        this.filenamePattern = filenamePattern;
    }

    public void scan(FileScanningAction fileScanningAction) {
        PatternBasedFilenameFilter filter = new PatternBasedFilenameFilter(filenamePattern);

        log.debug("Scanning files matching [" + filenamePattern + "] in [" + rootDir + "]");
        scanFiles(rootDir, filter, fileScanningAction);
    }

    private void scanFiles(SearchDirectory parentDir, FilenameFilter filter, FileScanningAction fileScanningAction) {
        List<SearchFile> files = parentDir.listFiles(filter);
        if (files != null) {
            for (SearchFile file : files) {
                fileScanningAction.scanFile(file);
            }
        }
        List<SearchDirectory> directories = parentDir.listDirectories();
        if (directories != null) {
            for(SearchDirectory directory : directories) {
                scanFiles(directory, filter, fileScanningAction);
            }
        }
    }


}
