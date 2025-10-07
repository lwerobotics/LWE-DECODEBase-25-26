package org.firstinspires.ftc.teamcode.resources.subsystems.outtake;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.hardware.motors.Motor;
import com.seattlesolvers.solverslib.hardware.motors.MotorEx;

public class Flywheels extends SubsystemBase {
    private MotorEx wheelMotor;

    /** Parameters for the initialization function of the given flywheel motor (updated 10/7/25)
     * @param hMap The hardware map used to register hardware into the robot (like motors, servos , actuators, etc.)
     * @param name The string name for the given motor ('flywheelL', 'flywheelR', etc.)
     */
    public void init(HardwareMap hMap, String name) {
        /* motor mapping */
        wheelMotor = new MotorEx(hMap, name);
        /* motor run-mode */
        wheelMotor.setRunMode(Motor.RunMode.RawPower);
    }

    /** Parameters for the function that actually revs up one of the two flywheels (you define which one and it will be in a parallel command group)
     * @param targetMotor
     * @param power
     */
    public void revMotorSingular(MotorEx targetMotor, double power) {
        this.wheelMotor = targetMotor;
        wheelMotor.set(power);
    }
}
