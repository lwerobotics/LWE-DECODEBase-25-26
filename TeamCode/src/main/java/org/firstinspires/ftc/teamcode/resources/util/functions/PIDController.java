package org.firstinspires.ftc.teamcode.resources.util.functions;

import com.qualcomm.robotcore.util.ElapsedTime;

@SuppressWarnings({"FieldCanBeLocal", "FieldMayBeFinal", "UnnecessaryLocalVariable"})
public class PIDController {
    private ElapsedTime timer = new ElapsedTime();
    private double kP, kI, kD;
    private double integralSum = 0.0;
    private double lastError = 0.0;
    private double error;
    public PIDController() {
        //la da dee la da doo
    }

    public double update(double reference, double motorState, double proportional, double integral, double derivative) {
        this.kP = proportional;
        this.kI = integral;
        this.kD = derivative;

        error = reference - motorState;
        integralSum += error * timer.seconds();
        double deriv = (error - lastError) / timer.seconds();

        //timer.reset();

        double output = (error * kP) + (deriv * kD)+ (integralSum * kI);
        return output;
    }

    public void resetController() {
        error = 0.0;
        integralSum = 0.0;
        lastError = 0.0;
        timer.reset();
    }
}