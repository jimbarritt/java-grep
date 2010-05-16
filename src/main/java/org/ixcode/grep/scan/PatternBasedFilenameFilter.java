package org.ixcode.grep.scan;

import java.io.*;
import java.util.regex.*;

public class PatternBasedFilenameFilter implements FilenameFilter {
    private Pattern pattern;

    public PatternBasedFilenameFilter(String filenamePattern) {
       String regex = ".*\\.txt";
       this.pattern = Pattern.compile(regex);              
    }

    @Override
    public boolean accept(File dir, String name) {
        return pattern.matcher(name).matches();
    }
}
