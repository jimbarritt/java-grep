package org.ixcode.grep;

import org.junit.*;

import java.io.*;
import java.nio.*;

import static org.hamcrest.core.Is.*;
import static org.junit.Assert.*;

public class CharBufferMatcherTest {

    @Test
    public void countsTheNumberOfLines() throws Exception {
        CharBufferMatcher matcher = new CharBufferMatcher(".*");
        CharBuffer someLinesOfText = writeSomeLinesInUtf8(
                "line 1",
                "line 2",
                "line 3");

        MatcherResult matcherResult = matcher.match(someLinesOfText);
        assertThat(matcherResult.processedLineCount(), is(3));
    }

    @Test
    public void matchesAPatternInALine() throws Exception {
        CharBufferMatcher matcher = new CharBufferMatcher(".*foobar\\:.*");
        CharBuffer someLinesOfText = writeSomeLinesInUtf8(
                        "line 1",
                        "some foobar: line 2",
                        "line 3");

        MatcherResult matcherResult = matcher.match(someLinesOfText);

        assertThat(matcherResult.matchedLineCount(), is(1));

        MatchedLine matchedLine = matcherResult.matchedLines(0);
        assertThat(matchedLine.lineText(), is("some foobar: line 2"));
    }

    @Test
    public void knowsWhereTheMatchedExpressionStartsAndEnds() throws Exception {
        CharBufferMatcher matcher = new CharBufferMatcher("foobar");
        CharBuffer someLinesOfText = writeSomeLinesInUtf8("some foobar: line 2");

        MatcherResult matcherResult = matcher.match(someLinesOfText);

        MatchedLine matchedLine = matcherResult.matchedLines(0);
        assertThat(matchedLine.start(), is(5));
        assertThat(matchedLine.end(), is(11));
    }



    @Test
    public void extractsGroupsFromExpression() throws Exception {
        CharBufferMatcher matcher = new CharBufferMatcher("(.*)foobar\\:.*");
        CharBuffer someLinesOfText = writeSomeLinesInUtf8("some foobar: line 2");

        MatcherResult matcherResult = matcher.match(someLinesOfText);

        MatchedLine matchedLine = matcherResult.matchedLines(0);

        assertThat(matchedLine.groups().size(), is(1));
        MatchedGroup matchedGroup = matchedLine.groups().get(0);
        assertThat(matchedGroup.text(), is("some "));
        assertThat(matchedGroup.start(), is(0));
        assertThat(matchedGroup.end(), is(5));        
    }

    private static CharBuffer writeSomeLinesInUtf8(String... lines) throws UnsupportedEncodingException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintWriter writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(out, "UTF-8")));
        try {
            for (String line : lines) {
                writer.println(line);
            }
        } finally {
            writer.flush();
            writer.close();
        }
        return CharBuffer.wrap(out.toString("UTF-8"));
    }
}
