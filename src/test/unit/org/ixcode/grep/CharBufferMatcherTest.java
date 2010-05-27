package org.ixcode.grep;

import org.junit.*;

import java.io.*;
import java.nio.*;

import static org.hamcrest.core.Is.*;
import static org.junit.Assert.*;

public class CharBufferMatcherTest {

    @Test
    public void countsTheNumberOfLines() throws Exception {
        CharBufferMatcher matcher = new CharBufferMatcher();

        CharBuffer charBuffer = writeSomeLinesInUtf8("line 1", "line 2", "line 3");

        int numberOfLines = matcher.match(charBuffer);

        assertThat(numberOfLines, is(3));
    }

    

    private static CharBuffer writeSomeLinesInUtf8(String... lines) throws UnsupportedEncodingException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintWriter writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(out, "UTF-8")));
        try {
            for (String line : lines) {
                writer.println(line);
            }
            return CharBuffer.wrap(out.toString("UTF-8"));
        } finally {
            writer.flush();
            writer.close();
        }
    }
}
