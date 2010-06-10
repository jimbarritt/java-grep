package org.ixcode.grep;

import org.apache.log4j.*;
import org.ixcode.grep.match.*;
import org.junit.*;

import java.util.*;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class GrepIntegrationTest {

    private static final Logger log = Logger.getLogger(GrepIntegrationTest.class);

    @Test    
    public void grepsFiles() {
        List<MatchedFile> matchedFiles = Grep.search("./src/test/resource/testfiles", "*.txt", "todo");

        assertThat(matchedFiles.size(), is(7));

        log.info("Results of search:\n");
        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        for (MatchedFile matchedFile : matchedFiles) {
            sb.append(printFile(matchedFile));
        }
        log.info(sb.toString());
    }

    private static String printFile(MatchedFile matchedFile) {
        StringBuilder sb = new StringBuilder();

        for (MatchedLine matchedLine : matchedFile.matchedLines()) {
            sb.append(matchedFile.fileName())
                    .append(":").append(matchedLine.lineNumber())
                    .append(":").append(matchedLine.start())
                    .append("\n");
            sb.append(matchedLine.lineText())
                    .append("\n");
        }
        return sb.toString();
    }

}
