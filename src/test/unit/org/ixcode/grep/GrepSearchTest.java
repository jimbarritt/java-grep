package org.ixcode.grep;

import org.ixcode.grep.scan.*;
import org.junit.*;

import static org.mockito.Mockito.*;

public class GrepSearchTest {

    @Test
    public void scansAFile() {
        GrepSearch grepSearch = new GrepSearch("foobar");

        SearchFile testFile = mock(SearchFile.class);

//        when(testFile.readFile()).thenReturn()
        grepSearch.scanFile(testFile);
    }


}
