package org.firstinspires.ftc.teamcode.resources.util.functions;

import org.firstinspires.ftc.teamcode.resources.util.enums.RobotConstants;

/* Courtesy of Tyler Mercado */
public class FilterStickInput {

    /**
     * @param input The number value input of the joystick
     * @return Returns the adjusted input value of the joystick to the drivetrain
     */

    public double filterStickInput(float input) {
        if (Math.abs(input) < RobotConstants.STICK_DEADZONE.getEnumValue()) { return 0.0; }
        double adjustedInput = (Math.abs(input) - RobotConstants.STICK_DEADZONE.getEnumValue()) / (1 - RobotConstants.STICK_DEADZONE.getEnumValue());
        return Math.pow(adjustedInput, RobotConstants.STICK_SENSITIVITY.getEnumValue()) * Math.signum(input);
    }
}