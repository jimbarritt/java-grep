package org.ixcode.grep;

import org.junit.*;

import java.util.*;

import static org.hamcrest.core.Is.*;
import static org.junit.Assert.*;

public class GrepIntegrationTest {

    @Test
    @Ignore("Not ready for this yet:)")
    public void grepsFiles() {
        List<MatchedFile> matchedFiles = Grep.search("./src/test/resource/testfiles", "*.txt");

        assertThat(matchedFiles.size(), is(1));                
    }

}
