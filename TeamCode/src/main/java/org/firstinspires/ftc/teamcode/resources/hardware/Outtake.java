package org.firstinspires.ftc.teamcode.resources.hardware;

import androidx.annotation.NonNull;

import com.bylazar.telemetry.TelemetryManager;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.hardware.motors.Motor;
import com.seattlesolvers.solverslib.hardware.motors.MotorEx;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.resources.util.enums.TelemetryStates;

public class Outtake extends SubsystemBase {
    public MotorEx leftFlywheel, rightFlywheel;

    /** Parameters for the initialization function of the given flywheel motor (updated 10/16/25)
     * @param hMap The hardware map used to register hardware to the robot (like motors, servos , actuators, etc.)
     * @return Returns the initialized motor from the function
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
        panels.addData("Left flywheel: ", TelemetryStates.INITIALIZED.toString());
        panels.addData("Right flywheel: ", TelemetryStates.INITIALIZED.toString());
        ftc.addData("Left flywheel: ", TelemetryStates.INITIALIZED);
        ftc.addData("Right flywheel: ", TelemetryStates.INITIALIZED);

        panels.update();
        ftc.update();
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