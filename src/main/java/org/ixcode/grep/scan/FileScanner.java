package org.ixcode.grep.scan;

import org.apache.log4j.*;

import java.io.*;

public class FileScanner {
    private static final Logger log = Logger.getLogger(FileScanner.class);

    private static final FileFilter DIRECTORY_FILTER = new DirectoryFilter();
    private final File rootDir;
    private final FilenamePattern filenamePattern;

    public FileScanner(SearchDirectory rootDir, FilenamePattern filenamePattern) {
        this.rootDir = rootDir.asFile();                
        this.filenamePattern = filenamePattern;
    }

    public void scan(FileScanningAction fileScanningAction) {
        PatternBasedFilenameFilter filter = new PatternBasedFilenameFilter(filenamePattern);

        log.debug("Scanning files matching [" + filenamePattern + "] in [" + rootDir.getAbsolutePath() + "]");
        scanFiles(rootDir, filter, fileScanningAction);
    }

    private void scanFiles(File parentDir, FilenameFilter filter, FileScanningAction fileScanningAction) {
        File[] files = parentDir.listFiles(filter);
        if (files != null) {
            for (File file : files) {
                fileScanningAction.scanFile(file);
            }
        }
        File[] directories = parentDir.listFiles(DIRECTORY_FILTER);
        if (directories != null) {
            for(File directory : directories) {
                scanFiles(directory, filter, fileScanningAction);
            }
        }
    }

    private static class DirectoryFilter implements FileFilter {
        @Override
        public boolean accept(File file) {
            return file.isDirectory();
        }
    }
}
