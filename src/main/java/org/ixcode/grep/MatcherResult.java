package org.ixcode.grep;

public class MatcherResult {
    private final int lineCount;

    public MatcherResult(int lineCount) {
        this.lineCount = lineCount;
    }

    public int lineCount() {
        return lineCount;
    }
}
