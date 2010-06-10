package org.ixcode.grep;

import org.ixcode.grep.match.*;

import java.util.*;

public class MatchedFile {
    private final String fileName;
    private final List<MatchedLine> matchedLines;

    public MatchedFile(String fileName, List<MatchedLine> matchedLines) {
        this.fileName = fileName;
        this.matchedLines = matchedLines;
    }

    public String fileName() {
        return fileName;
    }

    public List<MatchedLine> matchedLines() {
        return matchedLines;
    }
}
