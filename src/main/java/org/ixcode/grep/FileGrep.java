package org.ixcode.grep;

import java.io.*;
import java.nio.*;
import java.nio.channels.*;
import java.nio.charset.*;
import java.util.regex.*;

public class FileGrep {

    private static Charset charset = Charset.forName("UTF-8");
    private static CharsetDecoder decoder = charset.newDecoder();

    private static Pattern linePattern = Pattern.compile(".*\r?\n");

    private static Pattern pattern;

    private static void compile(String expression) {
        try {
            pattern = Pattern.compile(expression);
        } catch (PatternSyntaxException x) {
            System.err.println(x.getMessage());
            System.exit(1);
        }
    }

    private static void grep(File inputFile, CharBuffer cb) {
        Matcher lm = linePattern.matcher(cb);
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
                System.out.print(inputFile + ":" + lines + ":" + cs);
            }
            if (lm.end() == cb.limit()) {
                break;
            }
        }
    }

    private static void grep(File inputFile) throws IOException {
        FileInputStream fis = new FileInputStream(inputFile);
        FileChannel channel = fis.getChannel();

        try {
            int sz = (int) channel.size();
            MappedByteBuffer bb = channel.map(FileChannel.MapMode.READ_ONLY, 0, sz);

            CharBuffer cb = decoder.decode(bb);

            grep(inputFile, cb);
        } finally {
            channel.close();
        }
    }

    public static void main(String[] args) {
        if (args.length < 2) {
            System.err.println("Usage: java FileGrep pattern file...");
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
