package org.ixcode.grep;

public class MatchedGroup {
    private final String text;

    public MatchedGroup(String text) {
        this.text = text;
    }

    public String text() {
        return text;
    }
}
