package org.ixcode.grep;

import org.junit.*;

import java.util.*;

import static org.hamcrest.core.Is.*;
import static org.ixcode.grep.OperatingSystemName.*;
import static org.junit.Assert.*;

public class SystemLineSeparatorTest {

    @Before
    public void setup() {
    }


    @Test
    public void whatIsTheLineSeparator() {
        String lineSeparator = System.getProperty("line.separator");

        String expectedLineSeparator = mapExpectedLineSeparators()
                .add(WINDOWS, "\r\n")
                .add(OSX, "\n")
                .from(currentOperatingSystem());

        assertThat(lineSeparator, is(expectedLineSeparator));
    }

    private MapOfEntries mapExpectedLineSeparators() {
        return new MapOfEntries();
    }

    private static class MapOfEntries {
        private final Map<OperatingSystemName, String> entries = new HashMap<OperatingSystemName, String>();

        public MapOfEntries add(final OperatingSystemName os, final String lineSeparator) {
            entries.put(os, lineSeparator);
            return this;
        }

        public String from(OperatingSystemName operatingSystemName) {
            if (entries.containsKey(operatingSystemName)) {
                return entries.get(operatingSystemName);
            }
            throw new IllegalArgumentException("No record of the expected line separator for os [" + operatingSystemName + "]");
        }
    }


}