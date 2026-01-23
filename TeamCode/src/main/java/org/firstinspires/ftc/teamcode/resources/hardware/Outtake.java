package org.firstinspires.ftc.teamcode.resources.hardware;

import androidx.annotation.NonNull;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;

import org.firstinspires.ftc.teamcode.resources.util.enums.HardwareStates;

@SuppressWarnings("FieldCanBeLocal")
public class Outtake {
    public DcMotorEx leftFlywheel, rightFlywheel;
    public HardwareStates state = HardwareStates.NULL;
    public double power;
    private final PIDFCoefficients pidfCoefficients = new PIDFCoefficients(60.00, 0.00, 0.00, 14.50);

    public void initOuttake(@NonNull HardwareMap hMap) {
        /* motor config */
        leftFlywheel = hMap.get(DcMotorEx.class, "leftFlywheel");
        rightFlywheel = hMap.get(DcMotorEx.class, "rightFlywheel");

        leftFlywheel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        rightFlywheel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        rightFlywheel.setDirection(DcMotorSimple.Direction.REVERSE);

        leftFlywheel.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFlywheel.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftFlywheel.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightFlywheel.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        /* state control+power settings */
        state = HardwareStates.INITIALIZED;
        leftFlywheel.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, pidfCoefficients);
    }

    public void on(double target) {
        leftFlywheel.setVelocity(-target);
        rightFlywheel.setVelocity(-target);
        state = HardwareStates.ON;
    }

    public void off() {
        leftFlywheel.setPower(0.0);
        rightFlywheel.setPower(0.0);
        state = HardwareStates.OFF;
    }
}