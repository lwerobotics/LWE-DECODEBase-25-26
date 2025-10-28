package org.firstinspires.ftc.teamcode.resources.hardware;

import androidx.annotation.NonNull;

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
    public void initOuttake(@NonNull HardwareMap hMap) {
        /* motor mapping */
        leftFlywheel = new MotorEx(hMap, "leftFlywheel");
        rightFlywheel = new MotorEx(hMap, "rightFlywheel");
        /* motor run-modes */
        leftFlywheel.setRunMode(Motor.RunMode.RawPower);
        rightFlywheel.setRunMode(Motor.RunMode.RawPower);
        /* motor behaviors */
        leftFlywheel.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        rightFlywheel.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
    }

    public void on(double power) {
        leftFlywheel.set(power * -1);
        rightFlywheel.set(power);
    }

    public void off() {
        leftFlywheel.stopMotor();
        rightFlywheel.stopMotor();
    }
}