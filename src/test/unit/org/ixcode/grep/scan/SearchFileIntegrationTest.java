package org.ixcode.grep.scan;

import org.junit.*;

import java.io.*;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class SearchFileIntegrationTest {

    @Test
    public void readsAFile() throws Exception {
        File file = new File("./src/test/resource/testfiles/very_short.txt");

        SearchFile searchFile = new SearchFile(file);

        String fileContents = searchFile.readFile().toString();

        assertThat(fileContents, is("haiku\ntwoline"));
    }
}
