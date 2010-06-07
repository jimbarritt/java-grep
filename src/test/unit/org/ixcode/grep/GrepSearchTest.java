package org.ixcode.grep;

import org.ixcode.grep.match.*;
import org.ixcode.grep.scan.*;
import org.junit.*;

import java.util.*;

import static org.hamcrest.core.Is.*;
import static org.ixcode.grep.scan.FileScannerTest.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class GrepSearchTest {

    @Test
    public void scansAFile() {
        CharSequenceMatcher charSequenceMatcher = mock(CharSequenceMatcher.class);
        GrepSearch grepSearch = new GrepSearch(charSequenceMatcher);

        SearchFile searchFile1 = stubSearchFile("file1", "hello");
        SearchFile searchFile2 = stubSearchFile("file2", "hello again");

        when(charSequenceMatcher.match("hello")).thenReturn(matched());
        when(charSequenceMatcher.match("hello again")).thenReturn(notMatched());

        grepSearch.scanFile(searchFile1);
        grepSearch.scanFile(searchFile2);

        List<MatchedFile> matchedFiles = grepSearch.matchedFiles();

        assertThat(matchedFiles.size(), is(1));
    }

    private static MatcherResult matched() {
        return new MatcherResultStub(1);
    }

    private static MatcherResult notMatched() {
        return new MatcherResultStub(0);
    }

    private static class MatcherResultStub extends MatcherResult {
        private final int matchedLineCount;

        public MatcherResultStub(int matchedLineCount) {
            super(-1, new ArrayList<MatchedLine>());
            this.matchedLineCount = matchedLineCount;
        }

        @Override public int matchedLineCount() {
            return matchedLineCount;
        }
    }


}
