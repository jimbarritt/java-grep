package org.ixcode.grep.scan;

import org.junit.*;

import java.io.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class RegexFilenameFilterTest {

    @Test
    public void matchesAFilenameByRegex() {
        FilenameFilter filter = new RegexFilenameFilter(".*\\.txt");

        assertThat(filter.accept(null, "john.txt"), is(true));
        assertThat(filter.accept(null, "john.java"), is(false));
        assertThat(filter.accept(null, "john"), is(false));
        assertThat(filter.accept(null, "john.foo"), is(false));
    }
}
