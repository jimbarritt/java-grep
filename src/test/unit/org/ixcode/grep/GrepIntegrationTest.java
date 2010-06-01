package org.ixcode.grep;

import org.ixcode.grep.scan.*;
import org.junit.*;

import java.io.*;
import java.util.*;

import static org.hamcrest.core.Is.*;
import static org.junit.Assert.*;

public class GrepIntegrationTest {

    @Test
    public void grepsFiles() {
        Grep grep = new Grep(new FileScanner(projectFileFrom("/src/test/resource/testfiles"), new FilenamePattern("*.txt")));

        List<MatchedFile> matchedFiles = grep.searchFor(new GrepSearch("todo"));

        assertThat(matchedFiles.size(), is(1));                
    }

    private static SearchDirectory projectFileFrom(String path) {
        return new SearchDirectory(new File(projectRootDir(), path).getAbsolutePath());
    }

    public static File projectRootDir() {
        return new File(".");
    }
}
