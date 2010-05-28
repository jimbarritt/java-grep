package org.ixcode.grep;

import java.util.*;
import java.util.regex.*;

public class MatchedLine {
    private final CharSequence line;
    private List<String> groups;

    public MatchedLine(CharSequence line, MatchResult matchResult) {
        this.line = line;        
        this.groups = extractGroups(matchResult);
    }

    private List<String> extractGroups(MatchResult matchResult) {
        List<String> groups = new ArrayList<String>();
        for (int iGroup=0;iGroup<matchResult.groupCount();++iGroup) {
            groups.add(matchResult.group(iGroup));
        }
        return groups;
    }

    public String lineText() {
        return line.toString();
    }

    public List<String> groups() {
        return this.groups;
    }
}
