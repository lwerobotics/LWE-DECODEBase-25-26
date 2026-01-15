package org.firstinspires.ftc.teamcode.opmodes.teleop;

import com.bylazar.configurables.annotations.Configurable;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;

import org.firstinspires.ftc.teamcode.resources.hardware.Outtake;

@Configurable
@TeleOp(group = "Utility Tele-Ops", name = "Flywheel Tuner")
public class FlywheelTuner extends OpMode {
    public static double P = 0.0;
    public static double F = 0.0;
    private double highVel = 1200;
    private double lowVel = 900;
    private double currentTarget;
    private double[] stepSizes;
    private int index;
    private double curPrecision;
    private Outtake outtake;
    private Gamepad tunerOp;

    @Override
    public void init() {
        outtake = new Outtake();
        tunerOp = gamepad1;
        currentTarget = highVel;
        stepSizes = new double[]{10.0, 1.0, 0.1, 0.001, 0.0001};

        PIDFCoefficients pidfCoefficients = new PIDFCoefficients(P, 0.0, 0.0, F);
        outtake.initOuttake(hardwareMap);
        outtake.leftFlywheel.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, pidfCoefficients);
        outtake.rightFlywheel.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, pidfCoefficients);
    }

    @Override
    public void loop() {
        if (tunerOp.yWasPressed()) {
            if (currentTarget == highVel) {
                currentTarget = lowVel;
            } else {
                currentTarget = highVel;
            }
        }

        if (tunerOp.bWasPressed()) {
            index = (index + 1) % stepSizes.length;
        }

        if (tunerOp.dpadLeftWasPressed()) {
            F -= stepSizes[index];
        }
        if (tunerOp.dpadRightWasPressed()) {
            F += stepSizes[index];
        }

        if (tunerOp.dpadUpWasPressed()) {
            P += stepSizes[index];
        }
        if (gamepad1.dpadDownWasPressed()) {
            P -= stepSizes[index];
        }

        outtake.leftFlywheel.setVelocity(currentTarget);
        outtake.rightFlywheel.setVelocity(currentTarget);

        double curVel = outtake.leftFlywheel.getVelocity();
        double error = currentTarget - curVel;

        //add telemetry later
    }
}
