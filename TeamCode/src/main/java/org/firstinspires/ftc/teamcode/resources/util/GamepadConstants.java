package org.firstinspires.ftc.teamcode.resources.util;

public enum GamepadConstants {
    STICK_DEADZONE(0.05),
    STICK_SENSITIVITY(2.0);
    private final double value;

    private GamepadConstants(double value) {
        this.value = value;
    }

    public double getEnumValue() {
        return value;
    }
}
