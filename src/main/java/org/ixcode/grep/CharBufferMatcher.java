package org.ixcode.grep;

import java.nio.*;
import java.util.regex.*;

public class CharBufferMatcher {
    private static final Pattern LINE_PATTERN = Pattern.compile(".*\r?\n");
    
    public int match(CharBuffer charBuffer) {
        Matcher lm = LINE_PATTERN.matcher(charBuffer);
        int lines = 0;
        while (lm.find()) {
            lines++;            
            if (lm.end() == charBuffer.limit()) {
                break;
            }
        }
        return lines;
    }
}
