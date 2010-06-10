package org.ixcode.grep.scan;

import org.junit.*;

import java.io.*;
import java.nio.charset.*;

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

    @Test(expected=RuntimeException.class)
    public void wrapsIoExceptions() {
        File file = new File("foomcbar");

        SearchFile searchFile = new SearchFile(file);

        searchFile.readFile();
    }

    @Test(expected=RuntimeException.class)
    public void wrapsDecodingExceptions() throws Exception {
        File f = createTempFile("fotler");

        String nonUtf8EncodedContents = "Some dodgy encoding £ sign always stuffs up utf8";

        FileOutputStream fos = new FileOutputStream(f);
        fos.write(nonUtf8EncodedContents.getBytes(Charset.forName("foobar")));


    }

    private static File createTempFile(String fileName) throws IOException {
        File tempFile = File.createTempFile(fileName, "temp");
        boolean success = tempFile.createNewFile();
        if (!success) {
            throw new RuntimeException("Could not create temp file " + tempFile.getAbsolutePath());            
        }
        return tempFile;
    }
}
