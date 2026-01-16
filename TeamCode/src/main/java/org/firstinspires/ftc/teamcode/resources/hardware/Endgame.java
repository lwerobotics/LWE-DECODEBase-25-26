package org.firstinspires.ftc.teamcode.resources.hardware;

import androidx.annotation.NonNull;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.resources.util.enums.HardwareStates;

public class Endgame {
    public HardwareStates state = HardwareStates.NULL;
    private DcMotor leftSlide;
    private DcMotor rightSlide;

    public void initMotors(@NonNull HardwareMap hMap) {
        /* motor declaration+config */
        leftSlide = hMap.get(DcMotor.class, "leftSlide");
        rightSlide = hMap.get(DcMotor.class, "rightSlide");
        leftSlide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightSlide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        /* run-mode reset+config */
        leftSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftSlide.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightSlide.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        /* state control */
        state = HardwareStates.INITIALIZED;
    }

    public void extend() {
        leftSlide.setPower(1.0);
        rightSlide.setPower(-1.0);
        state = HardwareStates.ON;
    }

    public void retract() {
        leftSlide.setPower(-1.0);
        rightSlide.setPower(1.0);
        state = HardwareStates.REVERSE;
    }

    public void brake() {
        leftSlide.setPower(0.0);
        rightSlide.setPower(0.0);
        state = HardwareStates.OFF;
    }
}
