package org.ixcode.grep.scan;

import org.apache.log4j.*;
import org.junit.*;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;


public class FileScannerIntegrationTest {

    private static final Logger log = Logger.getLogger(FileScannerIntegrationTest.class);

    @Test
    public void scansTheTestFiles() {
        FileScanner scanner = new FileScanner(new SearchDirectory("src/test/resource/testfiles"), new PatternBasedFilenameFilter(new FilenamePattern("*.txt")));
        FileScannerTest.FileCounter fileCounter = new FileScannerTest.FileCounter();
        scanner.scan(fileCounter);

        assertThat(fileCounter.fileCount(), is(7L));        
    }

}

