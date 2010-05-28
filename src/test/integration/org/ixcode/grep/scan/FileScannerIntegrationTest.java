package org.ixcode.grep.scan;

import org.apache.log4j.*;
import org.junit.*;

import java.io.*;

import static org.hamcrest.core.Is.*;
import static org.junit.Assert.*;


public class FileScannerIntegrationTest {

    private static final Logger log = Logger.getLogger(FileScannerIntegrationTest.class);

    @Test
    public void scansTheTestFiles() {
        FileScanner scanner = new FileScanner(new File("src/test/resource/testfiles"), ".txt");
        FileCounter fileCounter = new FileCounter();
        scanner.scan(fileCounter);

        assertThat(fileCounter.fileCount(), is(7L));        
    }

    private static final class FileCounter implements FileScanningAction {

        private long fileCount;

        @Override
        public void scanFile(File file) {
            fileCount++;
            log.debug("Scanning file [" + file.getAbsolutePath() + "]");
        }

        public long fileCount() {
            return fileCount;
        }
    }
}

