package org.ixcode.grep.scan;

import org.apache.log4j.*;
import org.junit.*;

import static org.hamcrest.core.Is.*;
import static org.junit.Assert.*;


public class FileScannerIntegrationTest {

    private static final Logger log = Logger.getLogger(FileScannerIntegrationTest.class);

    @Test
    public void scansTheTestFiles() {
        FileScanner scanner = new FileScanner(new SearchDirectory("src/test/resource/testfiles"), new FilenamePattern("*.txt"));
        FileCounter fileCounter = new FileCounter();
        scanner.scan(fileCounter);

        assertThat(fileCounter.fileCount(), is(7L));        
    }

    private static final class FileCounter implements FileScanningAction {

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
}

