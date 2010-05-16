package org.ixcode.grep.scan;

import java.util.regex.*;

public class FilenamePattern {
    private final Pattern pattern;

    public FilenamePattern(String filenamePattern) {
        String regex = filenamePattern.replaceAll("\\.", "\\\\.").replaceAll("\\*", ".*");
        pattern = Pattern.compile(regex);
    }

    public Matcher matcher(String input) {
        return pattern.matcher(input);
    }
}
