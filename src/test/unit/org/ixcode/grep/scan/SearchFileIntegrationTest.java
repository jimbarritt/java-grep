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

    @Test(expected=DecodingContentException.class)
    public void wrapsDecodingExceptions() throws Exception {
        File tempFile = createTempFile("fotler");
        tempFile.deleteOnExit();

        String content = "Some dodgy encoding £ sign always stuffs up utf8";
        byte[] nonUtf8EncodedContents = content.getBytes(Charset.forName("windows-1252"));

        FileOutputStream fos = new FileOutputStream(tempFile);
        try {
            fos.write(nonUtf8EncodedContents);
            fos.flush();
        } finally {
            fos.close();
        }

        SearchFile searchFile = new SearchFile(tempFile);

        searchFile.readFile();

    }

    private static File createTempFile(String fileName) throws IOException {
        File tempFile = File.createTempFile(fileName, "temp");
        if (tempFile.exists()) {
            deleteFile(tempFile);
        }
        boolean success = tempFile.createNewFile();
        if (!success) {
            throw new RuntimeException("Could not create temp file " + tempFile.getAbsolutePath());            
        }
        return tempFile;
    }

    private static void deleteFile(File file) {
        if (file.delete()) {
            return;
        }
        throw new RuntimeException("Could not delete file: " + file.getAbsolutePath());
    }
}
