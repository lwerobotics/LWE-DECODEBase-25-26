package org.firstinspires.ftc.teamcode.resources.hardware;

import androidx.annotation.NonNull;

import com.bylazar.telemetry.TelemetryManager;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.hardware.motors.Motor;
import com.seattlesolvers.solverslib.hardware.motors.MotorEx;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.resources.util.enums.HardwareStates;

public class Outtake {
    private MotorEx leftFlywheel, rightFlywheel;
    private Telemetry telemetry;

    /** Parameters for the initialization function of the given flywheel motor (updated 10/16/25)
     * @param hMap The hardware map used to register hardware to the robot (like motors, servos , actuators, etc.)
     */
    public void initOuttake(@NonNull TelemetryManager panels, @NonNull Telemetry ftc, @NonNull HardwareMap hMap) {
        /* motor mapping */
        leftFlywheel = new MotorEx(hMap, "leftFlywheel");
        rightFlywheel = new MotorEx(hMap, "rightFlywheel");
        /* motor run-modes */
        leftFlywheel.setRunMode(Motor.RunMode.RawPower);
        rightFlywheel.setRunMode(Motor.RunMode.RawPower);
        /* motor behaviors */
        leftFlywheel.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        rightFlywheel.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        /* telemetry */
        this.telemetry = ftc;
        panels.addData("Left flywheel: ", HardwareStates.INITIALIZED.toString());
        panels.addData("Right flywheel: ", HardwareStates.INITIALIZED.toString());
        telemetry.addData("Left flywheel: ", HardwareStates.INITIALIZED);
        telemetry.addData("Right flywheel: ", HardwareStates.INITIALIZED);

        panels.update();
        telemetry.update();
    }

    public void on(double power) { //test this and edit as need be
        telemetry.addData("Did it run? ", "yur");
        telemetry.update();
        leftFlywheel.set(power * -1);
        rightFlywheel.set(power);
    }

    public void off() {
        telemetry.addData("Did it run? ", "yur");
        telemetry.update();
        leftFlywheel.stopMotor();
        rightFlywheel.stopMotor();
    }
}