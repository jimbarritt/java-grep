package org.ixcode.grep.match;

public class MatchedGroup {
    private final String text;
    private final int start;
    private final int end;

    public MatchedGroup(String text, int start, int end) {
        this.text = text;
        this.start = start;
        this.end = end;
    }

    public String text() {
        return text;
    }

    public int start() {
        return start;
    }

    public int end() {
        return end;
    }
}
