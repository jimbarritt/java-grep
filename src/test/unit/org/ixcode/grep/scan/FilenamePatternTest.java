package org.ixcode.grep.scan;

import org.junit.*;

import static org.hamcrest.core.Is.*;
import static org.junit.Assert.*;

public class FilenamePatternTest {

    @Test
    public void constructsAppropriateRegex() {
        FilenamePattern filenamePattern = new FilenamePattern("*.java");

        assertThat(filenamePattern.toString(), is(".*\\.java"));
        assertThat(filenamePattern.matches("john.java"), is(true));
        assertThat(filenamePattern.matches("john.txt"), is(false));
        assertThat(filenamePattern.matches("john"), is(false));
        assertThat(filenamePattern.matches("john.foo"), is(false));
    }

}
