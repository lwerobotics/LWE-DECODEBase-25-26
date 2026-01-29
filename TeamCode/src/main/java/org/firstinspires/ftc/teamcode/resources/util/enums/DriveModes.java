package org.firstinspires.ftc.teamcode.resources.util.enums;

public enum DriveModes {
    STANDARD("Standard"),
    DISTANCE("Distance"),
    TIME("Time");
    private final String value;

    DriveModes(String value) {
        this.value = value;
    }

    public String getEnumValue() {
        return value;
    }
}
