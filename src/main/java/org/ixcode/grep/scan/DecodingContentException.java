package org.ixcode.grep.scan;

import java.io.*;

public class DecodingContentException extends FileProcessingException {

    public DecodingContentException(File file, String characterEncoding, Throwable t) {
        super(createEncodingMessage(characterEncoding), file, t);
    }

    private static String createEncodingMessage(String characterEncoding) {
        return String.format("Could not decode file contents into %s", characterEncoding);
    }
}
