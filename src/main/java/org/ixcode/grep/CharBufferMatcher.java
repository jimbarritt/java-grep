package org.ixcode.grep;

import java.nio.*;
import java.util.regex.*;

public class CharBufferMatcher {
    private static final Pattern LINE_PATTERN = Pattern.compile(".*\r?\n");

    public CharBufferMatcher(String searchRegEx) {
    }

    public MatcherResult match(CharBuffer charBuffer) {
        Matcher lm = LINE_PATTERN.matcher(charBuffer);
        int lineCount = 0;
        while (lm.find()) {
            lineCount++;
            if (lm.end() == charBuffer.limit()) {
                break;
            }
        }
        return new MatcherResult(lineCount);
    }
}
