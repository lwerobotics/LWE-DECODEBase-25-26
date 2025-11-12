package org.firstinspires.ftc.teamcode.resources.util.functions;

import com.bylazar.telemetry.TelemetryManager;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.util.ElapsedTime;

@SuppressWarnings("FieldCanBeLocal")
public class PIDFController {
    private ElapsedTime timer = new ElapsedTime();
    private final TelemetryManager panelsTelemetry;
    private DcMotorEx targetMotor;
    private double referenceVelocity = 5250; //example #
    private double integralSum = 0.0;
    private double lastError = 0.0;
    private double kP, kI, kD;
    public PIDFController(double p, double i, double d, DcMotorEx motor, TelemetryManager panels) {
        this.panelsTelemetry = panels;
        this.targetMotor = motor;
        this.kP = p;
        this.kI = i;
        this.kD = d;
    }

    public double update() { //ask baron or vin on proper implementation later
        double currentVelocity = targetMotor.getVelocity();
        double error = referenceVelocity - currentVelocity;

        double derivative = (error - lastError) / timer.seconds();
        double integral = integralSum + (error * timer.seconds());
        double out = (kP * error) + (kI * integralSum) + (kD * derivative);

        return out; //edit this bruh
    }
}
