package org.ixcode.grep.scan;

import java.io.*;

public class PatternBasedFilenameFilter implements FilenameFilter {
    private FilenamePattern pattern;

    public static PatternBasedFilenameFilter filterFilenamesBy(String filter) {
        return new PatternBasedFilenameFilter(new FilenamePattern(filter));
    }

    public PatternBasedFilenameFilter(FilenamePattern pattern) {
        this.pattern = pattern;
    }

    @Override
    public boolean accept(File dir, String name) {
        return pattern.matches(name);
    }


}
