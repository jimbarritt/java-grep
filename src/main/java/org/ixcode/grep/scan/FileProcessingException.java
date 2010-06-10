package org.ixcode.grep.scan;

import java.io.*;

public class FileProcessingException extends RuntimeException {

    public FileProcessingException(String message, File file, Throwable t) {
        super(createMessage(message, file), t);
    }

    private static String createMessage(String message, File file) {
        return String.format("%s while processing file: %s (See cause)", message, file.getAbsolutePath());
    }
}
