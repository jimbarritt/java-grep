package org.ixcode.grep.match;

import java.util.*;

public class MatcherResult {
    private final int processedLineCount;
    private final List<MatchedLine> matchedLines;

    public MatcherResult(int processedLineCount, List<MatchedLine> matchedLines) {
        this.processedLineCount = processedLineCount;
        this.matchedLines = matchedLines;
    }

    public int processedLineCount() {
        return processedLineCount;
    }

    public int matchedLineCount() {
        return matchedLines.size();
    }

    public MatchedLine matchedLine(int matchedLineIndex) {
        return matchedLines.get(matchedLineIndex);
    }

    public boolean matched() {
        return matchedLineCount() > 0;
    }
}
