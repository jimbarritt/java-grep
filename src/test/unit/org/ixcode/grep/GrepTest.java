package org.ixcode.grep;

import org.ixcode.grep.scan.*;
import org.junit.*;

import java.util.*;

import static org.hamcrest.core.Is.*;
import static org.ixcode.grep.Grep.SearchOption.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class GrepTest {

    @Test
    public void grepsFiles() {
        GrepWiring wiring = mock(GrepWiring.class);
        FileScanner fileScanner = mock(FileScanner.class);
        FileGrepScanningAction fileGrepScanningAction = mock(FileGrepScanningAction.class);

        List<MatchedFile> expectedMatchedFiles = new ArrayList<MatchedFile>();
        when(wiring.createFileScanner("/some/root/path", "*.java")).thenReturn(fileScanner);
        when(wiring.createFileGrepScanningAction("todo", CASE_INSENSITIVE)).thenReturn(fileGrepScanningAction);
        when(fileGrepScanningAction.getMatchedFiles()).thenReturn(expectedMatchedFiles);

        Grep grep = new Grep("/some/root/path", "*.java", wiring);
        List<MatchedFile> matchedFiles = grep.searchFor("todo", CASE_INSENSITIVE);

        verify(wiring).createFileScanner("/some/root/path", "*.java");
        verify(fileScanner).scan(fileGrepScanningAction);

        assertThat(matchedFiles, is(expectedMatchedFiles));
    }
}
