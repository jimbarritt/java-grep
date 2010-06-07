package org.ixcode.grep.match;

import java.util.*;
import java.util.regex.*;

public class CharSequenceMatcher {
    private static final Pattern LINE_PATTERN = Pattern.compile("(.*)\r?\n");
    private final Pattern searchPattern;

    public CharSequenceMatcher(String searchExpression) {
        searchPattern = Pattern.compile(searchExpression);
    }

    public MatcherResult match(CharSequence charSequence) {
        Matcher lineMatcher = LINE_PATTERN.matcher(charSequence);
        int processedLineCount = 0;
        List<MatchedLine> matchedLines = new ArrayList<MatchedLine>();

        while (lineMatcher.find()) {
            processedLineCount++;

            CharSequence line = lineMatcher.group(1);
            Matcher searchPatternMatcher = searchPattern.matcher(line);
            if (searchPatternMatcher.find()) {
                matchedLines.add(createMatchedLine(processedLineCount, line, searchPatternMatcher));
            }

            if (lineMatcher.end() == charSequence.length()) {
                break;
            }
        }
        return new MatcherResult(processedLineCount, matchedLines);
    }

    private static MatchedLine createMatchedLine(int lineNumber, CharSequence line, Matcher searchPatternMatcher) {
        return new MatchedLine(lineNumber, line, searchPatternMatcher.toMatchResult());
    }
}
