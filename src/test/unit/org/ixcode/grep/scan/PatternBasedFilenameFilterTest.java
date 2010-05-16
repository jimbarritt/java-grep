package org.ixcode.grep.scan;

import org.junit.*;
import org.mockito.Mock;

import java.io.*;

import static org.hamcrest.core.Is.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.*;

public class PatternBasedFilenameFilterTest {
    @Mock private FilenamePattern filenamePattern;


    @Before
    public void setup() {
        initMocks(this);
    }

    @Test
    public void acceptsAMatchingFile() {
        when(filenamePattern.matches("foobar")).thenReturn(true);

        FilenameFilter filter = new PatternBasedFilenameFilter(filenamePattern);

        assertThat(filter.accept(null, "foobar"), is(true));
    }

    @Test
    public void doesNotAcceptAFileThatDoesNotMatch() {
        when(filenamePattern.matches("foobar")).thenReturn(false);

        FilenameFilter filter = new PatternBasedFilenameFilter(filenamePattern);

        assertThat(filter.accept(null, "foobar"), is(false));
    }
}
