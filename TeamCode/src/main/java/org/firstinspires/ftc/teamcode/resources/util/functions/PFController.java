package org.firstinspires.ftc.teamcode.resources.util.functions;

import androidx.annotation.NonNull;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;

@SuppressWarnings({"FieldMayBeFinal", "FieldCanBeLocal"})
public class PFController {
    public double highVel, lowVel, targetVel, P, F;

    public PFController() {}

    public void initController(@NonNull DcMotorEx motor1, @NonNull DcMotorEx motor2, double high, double low, double proportional, double feedforward) {
        this.highVel = high;
        this.lowVel = low;
        this.targetVel = this.highVel;
        this.P = proportional;
        this.F = feedforward;
        /* set initial motor coeffs. */
        PIDFCoefficients pidfCoefficients = new PIDFCoefficients(P, 0.0, 0.0, F);
        motor1.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, pidfCoefficients);
        motor2.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, pidfCoefficients);
    }

    public void update(PIDFCoefficients opmodeCoeffs, DcMotorEx motor) {
        motor.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, opmodeCoeffs);
    }
}
