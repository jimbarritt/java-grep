package org.ixcode.grep;

public enum OperatingSystemName {
    OSX("Mac OS X"), WINDOWS("Windows");
    

    private static final String OS_NAME_SYSTEM_PROPERTY_KEY = "os.name";
    private final String identifier;
    
    private OperatingSystemName(String identifier) {
        this.identifier = identifier;    
    }

    public boolean is(OperatingSystemName operatingSystemName) {
        return this == operatingSystemName;
    }

    public static OperatingSystemName currentOperatingSystem() {
        String osname = System.getProperty(OS_NAME_SYSTEM_PROPERTY_KEY);
        for (OperatingSystemName name : values()) {
            if (name.matches(osname)) {
                return name;
            }
        }
        throw new IllegalStateException("Could not interpret the current OS name (-D" + OS_NAME_SYSTEM_PROPERTY_KEY + "=" + osname + ")");
    }

    public String toString() {
        return identifier;
    }

    private boolean matches(String osname) {
        return identifier.equals(osname);
    }
}
