package org.ixcode.grep;

import java.io.*;
import java.nio.*;
import java.nio.channels.*;
import java.nio.charset.*;
import java.util.regex.*;

/**
 * See http://java.sun.com/j2se/1.4.2/docs/guide/nio/example/index.html
 * 
 */
public class ExampleOfFileGrep {

    private static Charset charset = Charset.forName("UTF-8");
    private static CharsetDecoder decoder = charset.newDecoder();

    private static final Pattern LINE_PATTERN = Pattern.compile(".*\r?\n");

    private static Pattern pattern;

    private static void compile(String expression) {
        try {
            pattern = Pattern.compile(expression);
        } catch (PatternSyntaxException x) {
            System.err.println(x.getMessage());
            System.exit(1);
        }
    }

    private static void grep(String inputFileName, CharBuffer cb) {
        Matcher lm = LINE_PATTERN.matcher(cb);
        Matcher pm = null;
        int lines = 0;
        while (lm.find()) {
            lines++;
            CharSequence cs = lm.group();
            if (pm == null) {
                pm = pattern.matcher(cs);
            } else {
                pm.reset(cs);
            }
            if (pm.find()) {
                System.out.print(inputFileName + ":" + lines + ":" + cs);
            }
            if (lm.end() == cb.limit()) {
                break;
            }
        }
    }

    private static void grep(File inputFile) throws IOException {

        FileInputStream fis = new FileInputStream(inputFile);
        FileChannel channel = fis.getChannel();
        MappedByteBuffer bb = null;
        try {
            int sz = (int) channel.size();            
            bb = channel.map(FileChannel.MapMode.READ_ONLY, 0, sz);
        } finally {
            channel.close();
            fis.close();            
        }
        CharBuffer cb = decoder.decode(bb);
        grep(inputFile.getAbsolutePath(), cb);
    }

    public static void main(String[] args) {
        if (args.length < 2) {
            System.err.println("Usage: java ExampleOfFileGrep pattern file...");
            return;
        }
        compile(args[0]);
        for (int i = 1; i < args.length; i++) {
            File f = new File(args[i]);
            try {
                grep(f);
            } catch (IOException x) {
                System.err.println(f + ": " + x);
            }
        }
    }

}
