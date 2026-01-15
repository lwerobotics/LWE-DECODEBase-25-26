package org.firstinspires.ftc.teamcode.resources.util.enums;

public enum GamepadConstants {
    STICK_DEADZONE(0.05),
    STICK_SENSITIVITY(2.0),
    TURN_SENSITIVITY(0.85),
    SLOW_TURN(0.55),
    NORMAL(1.0),
    SLOW(0.50);
    private final double value;

    GamepadConstants(double value) {
        this.value = value;
    }

    public double getEnumValue() {
        return value;
    }
}
