package org.ixcode.grep;

import org.ixcode.grep.scan.*;
import org.junit.*;

public class GrepSearchTest {

    @Test
    public void scansAFile() {
        GrepSearch grepSearch = new GrepSearch("foobar");

        SearchFile testFile;
        //todo cant do this until we refactor out files from filescanner
//        grepSearch.scanFile(testFile);
    }


}
