package org.ixcode.grep;

import org.ixcode.grep.match.*;
import org.ixcode.grep.scan.*;

import java.util.*;

public class GrepSearch implements FileScanningAction {
    private final CharSequenceMatcher charSequenceMatcher;

    private final List<MatchedFile> matchedFiles = new ArrayList<MatchedFile>();

    public GrepSearch(CharSequenceMatcher charSequenceMatcher) {
        this.charSequenceMatcher = charSequenceMatcher;
    }

    @Override
    public void scanFile(SearchFile searchFile) {
        MatcherResult matcherResult = charSequenceMatcher.match(searchFile.readFile());
        if (matcherResult.matched()) {
            matchedFiles.add(new MatchedFile(searchFile.toString(), matcherResult.matchedLines()));
        }
    }

    public List<MatchedFile> matchedFiles() {
        return matchedFiles;
    }

    public FileScanningAction fileScanningAction() {
        return this;
    }

}
