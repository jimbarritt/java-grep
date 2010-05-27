package org.ixcode.grep;

import org.junit.*;

import static org.hamcrest.core.Is.*;
import static org.ixcode.grep.OperatingSystemName.*;
import static org.junit.Assert.*;

public class OperatingSystemNameTest {

    @Test
    public void knowsItIsOsx() {
        System.setProperty("os.name", "Mac OS X");

        OperatingSystemName operatingSystemName = currentOperatingSystem();

        assertThat(operatingSystemName.is(OSX), is(true));
    }

    @Test
    public void knowsItIsWindows() {
        System.setProperty("os.name", "Windows");

        OperatingSystemName operatingSystemName = currentOperatingSystem();

        assertThat(operatingSystemName.is(WINDOWS), is(true));
    }


    @Test(expected = IllegalStateException.class)
    public void failsIfOsNameDoesNotExist() {
        System.setProperty("os.name", "foobar");

        currentOperatingSystem();
    }

    @Test
    public void knowsItsWindows() {
        System.setProperty("os.name", "Windows");

        OperatingSystemName operatingSystemName = currentOperatingSystem();

        assertThat(operatingSystemName.is(WINDOWS), is(true));
    }

    @Test
    public void knowsItsName() {
        assertThat(OSX.toString(), is("Mac OS X"));
    }

    @Test
    public void canBeComparedToAnotherOsName() {
        assertThat(OSX.is(OSX), is(true));
        assertThat(OSX.is(WINDOWS), is(false));
    }

}