package org.ixcode.grep.scan;

import java.io.*;
import java.util.regex.*;

public class RegexFilenameFilter implements FilenameFilter {
    private Pattern pattern;

    public RegexFilenameFilter(String regularExpression) {
       this.pattern = Pattern.compile(regularExpression);              
    }

    @Override
    public boolean accept(File dir, String name) {
        return pattern.matcher(name).matches();
    }
}
