package org.ixcode.grep.scan;

import org.apache.log4j.*;

import java.io.*;
import java.util.*;

public class FileScanner {
    private static final Logger log = Logger.getLogger(FileScanner.class);

    
    private final SearchDirectory rootDir;
    private final FilenameFilter filenameFilter;

    public FileScanner(SearchDirectory rootDir, FilenameFilter filenameFilter) {
        this.rootDir = rootDir;
        this.filenameFilter = filenameFilter;
    }

    public void scan(FileScanningAction fileScanningAction) {
        log.debug("Scanning files matching [" + filenameFilter + "] in [" + rootDir + "]");
        scanFiles(rootDir, filenameFilter, fileScanningAction);
    }

    private void scanFiles(SearchDirectory parentDir, FilenameFilter filter, FileScanningAction fileScanningAction) {
        List<SearchFile> files = parentDir.listFiles(filter);
        if (files != null) {
            for (SearchFile file : files) {
                fileScanningAction.scanFile(file);
            }
        }
        List<SearchDirectory> subDirectories = parentDir.listSubDirectories();
        if (subDirectories != null) {
            for(SearchDirectory subDirectory : subDirectories) {
                scanFiles(subDirectory, filter, fileScanningAction);
            }
        }
    }


}
