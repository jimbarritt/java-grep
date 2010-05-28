package org.ixcode.grep;

public class MatchedLine {
    private final CharSequence line;

    public MatchedLine(CharSequence line) {
        this.line = line;
    }

    public String lineText() {
        return line.toString();
    }
}