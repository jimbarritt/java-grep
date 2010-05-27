package org.ixcode.grep;

import org.junit.*;

import java.io.*;
import java.nio.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class CharBufferMatcherTest {
   
    @Test
    public void returnsNumberOfLines() throws Exception {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintWriter writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(out, "UTF-8")));
        writer.println("line 1");
        writer.println("line 2");
        writer.println("line 3");

        writer.flush();
        writer.close();

        CharBufferMatcher matcher = new CharBufferMatcher();

        int numberOfLines = matcher.match(CharBuffer.wrap(out.toString("UTF-8")));

        assertThat(numberOfLines, is(3));
    }
}
