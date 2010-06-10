package org.ixcode.grep.os;

import org.apache.log4j.*;
import org.junit.*;

import java.nio.charset.*;
import java.util.*;

public class CharsetTest {

    private static final Logger log = Logger.getLogger(CharsetTest.class);

    @Test
    public void listAllCharsets() {
        Map<String,Charset> charsets = Charset.availableCharsets();
        for (Charset charset : charsets.values()) {
            log.info(charset.displayName());
        }
    }

}
