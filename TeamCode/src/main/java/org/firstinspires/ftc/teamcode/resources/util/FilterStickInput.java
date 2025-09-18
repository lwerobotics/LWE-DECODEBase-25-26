package org.firstinspires.ftc.teamcode.resources.util;

public class FilterStickInput {
    public double filterStickInput(double input) {
        if (Math.abs(input) < GamepadConstants.STICKDEADZONE.getEnumValue()) { return 0.0; }
        double adjustedInput = (Math.abs(input) - GamepadConstants.STICKDEADZONE.getEnumValue()) / (1 - GamepadConstants.STICKDEADZONE.getEnumValue());
        return Math.pow(adjustedInput, GamepadConstants.STICKSENSITIVITY.getEnumValue()) * Math.signum(input);
    }
}