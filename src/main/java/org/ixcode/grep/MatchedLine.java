package org.ixcode.grep;

import java.util.*;
import java.util.regex.*;

public class MatchedLine {
    private final CharSequence line;
    private List<MatchedGroup> groups;
    private MatchResult matchResult;

    public MatchedLine(CharSequence line, MatchResult matchResult) {
        this.line = line;        
        this.groups = extractGroups(matchResult);
        this.matchResult = matchResult;
    }

    private List<MatchedGroup> extractGroups(MatchResult matchResult) {
        List<MatchedGroup> groups = new ArrayList<MatchedGroup>();
        for (int iGroup=1;iGroup<=matchResult.groupCount();++iGroup) {
            groups.add(new MatchedGroup(matchResult.group(iGroup), matchResult.start(iGroup), matchResult.end(iGroup)));
        }
        return groups;
    }

    public String lineText() {
        return line.toString();
    }

    public List<MatchedGroup> groups() {
        return this.groups;
    }


    public int start() {
        return matchResult.start();
    }

    public Integer end() {
        return matchResult.end();
    }
}
