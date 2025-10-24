package org.firstinspires.ftc.teamcode.resources.util.functions;

import org.firstinspires.ftc.teamcode.resources.util.enums.GamepadConstants;

public class FilterStickInput {
    public double filterStickInput(double input) {
        if (Math.abs(input) < GamepadConstants.STICK_DEADZONE.getEnumValue()) { return 0.0; }
        double adjustedInput = (Math.abs(input) - GamepadConstants.STICK_DEADZONE.getEnumValue()) / (1 - GamepadConstants.STICK_DEADZONE.getEnumValue());
        return Math.pow(adjustedInput, GamepadConstants.STICK_SENSITIVITY.getEnumValue()) * Math.signum(input);
    }
}