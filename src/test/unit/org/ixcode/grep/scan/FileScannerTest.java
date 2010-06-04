package org.ixcode.grep.scan;

import org.apache.log4j.*;
import org.junit.*;

import java.io.*;
import java.util.*;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class FileScannerTest {

    @Test
    public void scansADirectory() {
        SearchDirectory subDirectory = new SearchDirectoryStub()
                                                .withFile("subFoobarA.txt")
                                                .withFile("subFoobarB.txt");

        SearchDirectory rootDirectory = new SearchDirectoryStub()
                                                    .withFile("foobarA.txt")
                                                    .withFile("foobarB.java")
                                                    .withSubDirectory(subDirectory);

        FileScanner scanner = new FileScanner(rootDirectory, new FilenamePattern("*.txt"));

        FileCounter fileCounter = new FileCounter();

        scanner.scan(fileCounter);

        assertThat(fileCounter.fileCount(), is(3L));
    }

    static final class FileCounter implements FileScanningAction {
        private static final Logger log = Logger.getLogger(FileCounter.class);

        private long fileCount;

        @Override
        public void scanFile(SearchFile searchFile) {
            fileCount++;
            log.debug("Scanning file [" + searchFile + "]");
        }

        public long fileCount() {
            return fileCount;
        }


    }

    private static class SearchDirectoryStub extends SearchDirectory {
        private static final File EMPTY_FILE = new File("empty_file");

        List<SearchFile> searchFiles = new ArrayList<SearchFile>();
        List<SearchDirectory> subDirectories = new ArrayList<SearchDirectory>();

        public SearchDirectoryStub() {
            super(EMPTY_FILE);
        }

        SearchDirectoryStub withFile(String filename) {
            searchFiles.add(new SearchFileStub(filename));
            return this;
        }

        public SearchDirectoryStub withSubDirectory(SearchDirectory subDirectory) {
            subDirectories.add(subDirectory);
            return this;
        }

        @Override public List<SearchFile> listFiles(FilenameFilter filter) {
            List<SearchFile> foundFiles = new ArrayList<SearchFile>();        
            for (SearchFile searchFile : searchFiles) {
                File ioSearchFile = new File(searchFile.toString());
                if (filter.accept(ioSearchFile.getParentFile(), ioSearchFile.getName())) {
                    foundFiles.add(searchFile);
                }
            }
            return foundFiles;
        }

        @Override public List<SearchDirectory> listDirectories() {
            return subDirectories;
        }

        @Override public String toString() {
            return "StubDirectory";
        }
    }

    private static class SearchFileStub extends SearchFile {
        private final String filename;

        public SearchFileStub(String filename) {
            super(null);
            this.filename = filename;
        }

        @Override public String toString() {
            return filename;
        }
    }


}
