package org.ixcode.grep;

import java.nio.*;
import java.util.regex.*;

public class CharBufferMatcher {
    private static final Pattern LINE_PATTERN = Pattern.compile(".*\r?\n");
    private final Pattern searchPattern;

    public CharBufferMatcher(String searchExpression) {
        searchPattern = Pattern.compile(searchExpression);
    }

    public MatcherResult match(CharBuffer charBuffer) {
        Matcher lineMatcher = LINE_PATTERN.matcher(charBuffer);
        int processedLineCount = 0;
        int matchedLinesCount = 0;

        while (lineMatcher.find()) {
            processedLineCount++;

            CharSequence line = lineMatcher.group();
            Matcher searchPatternMatcher = searchPattern.matcher(line);
            if (searchPatternMatcher.find()) {
                matchedLinesCount++;
            }

            if (lineMatcher.end() == charBuffer.limit()) {
                break;
            }
        }
        return new MatcherResult(processedLineCount, matchedLinesCount);
    }
}
