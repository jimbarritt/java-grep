package org.ixcode.grep.scan;

import java.io.*;
import java.nio.*;
import java.nio.channels.*;
import java.nio.charset.*;

public class SearchFile {
    private static Charset UTF8_CHARSET = Charset.forName("UTF-8");
    private static CharsetDecoder UTF8_DECODER = UTF8_CHARSET.newDecoder();


    private final File delegate;

    public SearchFile(File delegate) {
        this.delegate = delegate;
    }

    public String toString() {
        return delegate.getAbsolutePath();
    }

    public CharSequence readFile() {
        MappedByteBuffer bb = null;
        try {
            FileInputStream fis = new FileInputStream(delegate);
            FileChannel channel = fis.getChannel();            
            try {
                int sz = (int) channel.size();
                bb = channel.map(FileChannel.MapMode.READ_ONLY, 0, sz);
            } finally {
                channel.close();
                fis.close();
            }
        } catch (IOException e) {
            throw new FileProcessingException("Could not read file", delegate, e);
        }

        try {
            return UTF8_DECODER.decode(bb);
        } catch (CharacterCodingException e) {
            throw new DecodingContentException(delegate, "UTF8", e);
        }
    }
}
