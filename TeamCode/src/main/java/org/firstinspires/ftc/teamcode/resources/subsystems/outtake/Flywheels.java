package org.firstinspires.ftc.teamcode.resources.subsystems.outtake;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.hardware.motors.Motor;
import com.seattlesolvers.solverslib.hardware.motors.MotorEx;

public class Flywheels extends SubsystemBase {
    public MotorEx leftFlywheel, rightFlywheel;

    /** Parameters for the initialization function of the given flywheel motor (updated 10/16/25)
     * @param hMap The hardware map used to register hardware to the robot (like motors, servos , actuators, etc.)
     * @return Returns the initialized motor from the function
     */
    public void init(HardwareMap hMap) {
        /* motor mapping */
        leftFlywheel = new MotorEx(hMap, "leftFlywheel");
        rightFlywheel = new MotorEx(hMap, "rightFlywheel");
        /* motor run-modes */
        leftFlywheel.setRunMode(Motor.RunMode.RawPower);
        rightFlywheel.setRunMode(Motor.RunMode.RawPower);
    }

    /** Parameters for the function that actually revs up one of the two flywheels (you define which one and it will be in a parallel command group) [updated 10/16/25]
     * @param targetMotor Either the left flywheel or the right flywheel
     * @param power The decimal power given to the motor (-# = reverse, +# = forward; range = [-1, 1])
     */
    public void revMotorSingular(MotorEx targetMotor, double power) {
        targetMotor.set(power);
    }
}
