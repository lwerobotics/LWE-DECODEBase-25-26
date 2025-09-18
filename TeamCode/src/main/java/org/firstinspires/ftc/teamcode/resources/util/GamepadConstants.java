package org.firstinspires.ftc.teamcode.resources.util;

public enum GamepadConstants {
    STICKDEADZONE(0.05),
    STICKSENSITIVITY(2.0);

    private final double value;

    private GamepadConstants(double value) {
        this.value = value;
    }

    public double getEnumValue() {
        return value;
    }
}
