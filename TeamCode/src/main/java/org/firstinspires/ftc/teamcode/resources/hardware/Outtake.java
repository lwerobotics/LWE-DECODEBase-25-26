package org.firstinspires.ftc.teamcode.resources.hardware;

import androidx.annotation.NonNull;

import com.bylazar.telemetry.TelemetryManager;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.resources.util.enums.HardwareStates;

public class Outtake {
    private DcMotor leftFlywheel, rightFlywheel;
    private Telemetry telemetry;

    /** Parameters for the initialization function of the given flywheel motor (updated 10/16/25)
     * @param hMap The hardware map used to register hardware to the robot (like motors, servos , actuators, etc.)
     */
    public void initOuttake(@NonNull TelemetryManager panels, @NonNull Telemetry ftc, @NonNull HardwareMap hMap) {
        /* motor mapping */
        leftFlywheel = hMap.get(DcMotor.class, "leftFlywheel");
        rightFlywheel = hMap.get(DcMotor.class, "rightFlywheel");

        leftFlywheel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightFlywheel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        leftFlywheel.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFlywheel.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftFlywheel.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightFlywheel.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
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
        leftFlywheel.setPower(-power);
        rightFlywheel.setPower(power);
    }

    public void off() {
        leftFlywheel.setPower(0.0);
        rightFlywheel.setPower(0.0);
    }
}