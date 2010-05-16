package org.ixcode.grep.scan;

import org.junit.*;

import java.util.regex.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class FilenamePatternTest {


    @Test
    public void constructsAppropriateRegex() {
        FilenamePattern filenamePattern = new FilenamePattern("*.java");

        Matcher matcher = filenamePattern.matcher("john.java");

        assertThat(matcher.pattern().pattern(), is(".*\\.java"));
        assertThat(matcher.matches(), is(true));
    }   

}
