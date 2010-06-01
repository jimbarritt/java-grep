package org.ixcode.grep;

import org.ixcode.grep.scan.*;
import org.junit.*;
import org.mockito.Mock;

import java.util.*;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.*;

public class GrepTest {
    private @Mock FileScanner fileScanner;
    private @Mock FileScanningAction fileScanningAction;
    private @Mock GrepSearch grepSearch;

    @Before
    public void onceBeforeEachTest() {
        initMocks(this);
    }

    @Test
    public void grepsFilesUsingTheFileScanner() {
        List<MatchedFile> expectedMatchedFiles = new ArrayList<MatchedFile>();

        when(grepSearch.fileScanningAction()).thenReturn(fileScanningAction);
        when(grepSearch.matchedFiles()).thenReturn(expectedMatchedFiles);
        
        Grep grep = new Grep(fileScanner);

        List<MatchedFile> matchedFiles = grep.searchFor(grepSearch);

        verify(fileScanner).scan(fileScanningAction);
        assertThat(matchedFiles, is(expectedMatchedFiles));
    }



}
