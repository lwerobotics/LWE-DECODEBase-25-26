package org.firstinspires.ftc.teamcode.resources.util.enums;

public enum GamepadConstants {
    STICK_DEADZONE(0.05),
    STICK_SENSITIVITY(2.0),
    TURN_SENSITIVITY(0.8);
    private final double value;

    private GamepadConstants(double value) {
        this.value = value;
    }

    public double getEnumValue() {
        return value;
    }
}
