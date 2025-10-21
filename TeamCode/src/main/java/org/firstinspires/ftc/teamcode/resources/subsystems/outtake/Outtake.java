package org.firstinspires.ftc.teamcode.resources.subsystems.outtake;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.hardware.motors.Motor;
import com.seattlesolvers.solverslib.hardware.motors.MotorEx;

public class Outtake extends SubsystemBase {
    public MotorEx leftFlywheel, rightFlywheel;
    /** Parameters for the initialization function of the given flywheel motor (updated 10/16/25)
     * @param hMap The hardware map used to register hardware to the robot (like motors, servos , actuators, etc.)
     * @return Returns the initialized motor from the function
     */
    public void initOuttake(HardwareMap hMap) {
        /* motor mapping */
        leftFlywheel = new MotorEx(hMap, "leftFlywheel");
        rightFlywheel = new MotorEx(hMap, "rightFlywheel");
        /* motor run-modes */
        leftFlywheel.setRunMode(Motor.RunMode.RawPower);
        rightFlywheel.setRunMode(Motor.RunMode.RawPower);
    }

    public void on(double power) { //test ts numbers
        leftFlywheel.set(power);
        rightFlywheel.set(power);
    }

    public void off() {
        leftFlywheel.stopMotor();
        rightFlywheel.stopMotor();
    }
}
