package org.firstinspires.ftc.teamcode.resources.subsystems.intake;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.hardware.motors.Motor;
import com.seattlesolvers.solverslib.hardware.motors.MotorEx;

public class Collection extends SubsystemBase {
    private MotorEx collectionMotor;

    /**
     * Parameters for the initialization function of the given flywheel motor (updated 10/8/25)
     * @param hMap The hardware map used to register hardware to the robot (like motors, servos , actuators, etc.)
     * @param name The string name for the given motor ('flywheelL', 'flywheelR', etc.)
     * @return Returns the initialized motor from the function
     */
    public MotorEx init(HardwareMap hMap, String name) {
        /* motor mapping */
        collectionMotor = new MotorEx(hMap, name);
        /* motor run-mode */
        collectionMotor.setRunMode(Motor.RunMode.RawPower);
        return collectionMotor;
    }

    /** Parameters for the function that actually revs up one of the two flywheels (you define which one and it will be in a parallel command group) [updated 10/8/25]
     * @param targetMotor The given motor that controls the intake system
     */
    public void intake(MotorEx targetMotor, double power) {
        this.collectionMotor = targetMotor;
        collectionMotor.set(power);
    }
}
