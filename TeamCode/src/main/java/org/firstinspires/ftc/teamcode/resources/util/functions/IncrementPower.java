package org.firstinspires.ftc.teamcode.resources.util.functions;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class IncrementPower {
    private double power;
    private boolean negCheck;

    /** Parameters for the incrementing of motor power in 25% increments for debug purposes (updated 10/23/25)
     * @param targetPower The power value for the given motor(s) to be incremented
     * @param negativeCheck A boolean used to check whether to add or subtract 25% of power
     */
    public void incrementPower(double targetPower, boolean negativeCheck) {
        this.power = targetPower;
        this.negCheck = negativeCheck;

        if (power >= 0.0 && power <= 1.0) {
            power = power + 0.25;
            if (negCheck) {power = power - 0.5;} //ex. 0.5 -> 0.75 -> 0.25 (target: 0.25) [this is so scuffed holy moly]
        } else if (targetPower == 1.0) { //this is to stop any power from going above 1 (1 -> 100% motor power)
            power = targetPower + 0.0;
            if (negCheck) {power = power - 0.25;}
        }
    }
}
