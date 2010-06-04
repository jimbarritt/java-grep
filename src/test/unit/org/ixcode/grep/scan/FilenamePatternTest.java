package org.ixcode.grep.scan;

import org.junit.*;

import static org.hamcrest.core.Is.*;
import static org.junit.Assert.*;

public class FilenamePatternTest {
   
    @Test
    public void matchesJavaFiles() {
        FilenamePattern filenamePattern = new FilenamePattern("*.java");

        assertThat(filenamePattern.toString(), is(".*\\.java"));
        assertThat(filenamePattern.matches("john.java"), is(true));
        assertThat(filenamePattern.matches("john.txt"), is(false));
        assertThat(filenamePattern.matches("john"), is(false));
        assertThat(filenamePattern.matches("john.foo"), is(false));
    }

    @Test
    public void matchesTextFiles() {
        FilenamePattern filenamePattern = new FilenamePattern("*.txt");

        assertThat(filenamePattern.toString(), is(".*\\.txt"));
        assertThat(filenamePattern.matches("john.java"), is(false));
        assertThat(filenamePattern.matches("john.txt"), is(true));
        assertThat(filenamePattern.matches("john"), is(false));
        assertThat(filenamePattern.matches("john.foo"), is(false));
    }

}
