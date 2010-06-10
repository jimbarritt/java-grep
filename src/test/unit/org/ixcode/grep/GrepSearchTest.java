package org.ixcode.grep;

import org.ixcode.grep.match.*;
import org.ixcode.grep.scan.*;
import org.junit.*;
import org.mockito.Mock;

import java.util.*;

import static org.hamcrest.core.Is.*;
import static org.ixcode.grep.scan.FileScannerTest.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.*;

public class GrepSearchTest {
    @Mock private CharSequenceMatcher charSequenceMatcher;
    private GrepSearch grepSearch;

    @Before
    public void onceBeforeEachTest() {
        initMocks(this);
        grepSearch = new GrepSearch(charSequenceMatcher);
    }

    @Test
    public void returnsOnlyMatchedFiles() {
        SearchFile searchFile1 = stubSearchFile("file1", "hello");
        SearchFile searchFile2 = stubSearchFile("file2", "hello again");

        when(charSequenceMatcher.match("hello")).thenReturn(matched());
        when(charSequenceMatcher.match("hello again")).thenReturn(notMatched());

        grepSearch.scanFile(searchFile1);
        grepSearch.scanFile(searchFile2);

        List<MatchedFile> matchedFiles = grepSearch.matchedFiles();
        assertThat(matchedFiles.size(), is(1));
    }

    @Test
    public void reportsMatchedFilesCorrectly() {
        SearchFile searchFile1 = stubSearchFile("file1", "hello");
        when(charSequenceMatcher.match("hello")).thenReturn(matched());

        grepSearch.scanFile(searchFile1);

        List<MatchedFile> matchedFiles = grepSearch.matchedFiles();
        MatchedFile matchedFile = matchedFiles.get(0);
        assertThat(matchedFile.fileName(), is("file1"));
        assertThat(matchedFile.matchedLines().size(), is(1));
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
            super(-1, createLines(matchedLineCount));
            this.matchedLineCount = matchedLineCount;
        }

        @Override public int matchedLineCount() {
            return matchedLineCount;
        }

        private static List<MatchedLine> createLines(int matchedLineCount) {
            List<MatchedLine> matchedLines = new ArrayList<MatchedLine>();
            for (int i=0; i<matchedLineCount;++i) {
                matchedLines.add(null);                
            }
            return matchedLines;
        }

    }


}
