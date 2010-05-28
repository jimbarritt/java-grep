package org.ixcode.grep.os;

import org.junit.*;

import static org.hamcrest.core.Is.*;
import static org.ixcode.grep.os.OperatingSystemName.*;
import static org.junit.Assert.*;

public class OperatingSystemNameTest {

    public static final String OS_NAME_SYSTEM_PROPERTY_KEY = "os.name";
    private String originalOsName;

    @Before
    public void setUp() {
        originalOsName = System.getProperty(OS_NAME_SYSTEM_PROPERTY_KEY);
    }

    @After
    public void tearDown() {
        System.setProperty(OS_NAME_SYSTEM_PROPERTY_KEY, originalOsName);
    }

    @Test
    public void knowsItIsOsFromSystemProperty() {
        System.setProperty(OS_NAME_SYSTEM_PROPERTY_KEY, "Mac OS X");

        OperatingSystemName operatingSystemName = currentOperatingSystem();

        assertThat(operatingSystemName.is(OSX), is(true));
    }
    
    @Test(expected = IllegalStateException.class)
    public void failsIfOsNameDoesNotExist() {
        System.setProperty(OS_NAME_SYSTEM_PROPERTY_KEY, "foobar");

        currentOperatingSystem();
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