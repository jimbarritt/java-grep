package org.ixcode.grep;

public class MatcherResult {
    private final int processedLineCount;
    private final int matchedLinesCount;

    public MatcherResult(int processedLineCount, int matchedLinesCount) {
        this.processedLineCount = processedLineCount;
        this.matchedLinesCount = matchedLinesCount;
    }

    public int processedLineCount() {
        return processedLineCount;
    }

    public int matchedLineCount() {
        return matchedLinesCount;
    }
}
