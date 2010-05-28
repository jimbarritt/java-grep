package org.ixcode.grep.match;

import java.util.*;

public class MatcherResult {
    private final int processedLineCount;
    private final int matchedLinesCount;
    private final List<MatchedLine> matchedLines;

    public MatcherResult(int processedLineCount, int matchedLinesCount, List<MatchedLine> matchedLines) {
        this.processedLineCount = processedLineCount;
        this.matchedLinesCount = matchedLinesCount;
        this.matchedLines = matchedLines;
    }

    public int processedLineCount() {
        return processedLineCount;
    }

    public int matchedLineCount() {
        return matchedLinesCount;
    }

    public MatchedLine matchedLines(int matchedLineIndex) {
        return matchedLines.get(matchedLineIndex);
    }
}
