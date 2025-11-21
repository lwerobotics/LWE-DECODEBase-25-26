package org.firstinspires.ftc.teamcode.opmodes.auto;

import com.bylazar.telemetry.PanelsTelemetry;
import com.bylazar.telemetry.TelemetryManager;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.resources.hardware.Drivetrain;

@Autonomous(name = "TestAuto (one that actually has stuff)")
public class HORRENDOUS extends LinearOpMode {
    private Drivetrain drivetrain;
    private ElapsedTime runtime;
    private TelemetryManager panelsTelemetry = PanelsTelemetry.INSTANCE.getTelemetry();

    @Override
    public void runOpMode() throws InterruptedException {
        runtime = new ElapsedTime();
        drivetrain = new Drivetrain();

        drivetrain.initAutonomousDrive(hardwareMap, panelsTelemetry, telemetry);
        waitForStart();

        while (opModeIsActive() && runtime.seconds() > 1.0) {
            drivetrain.leftFront.setPower(-1);
            drivetrain.leftRear.setPower(1);
            drivetrain.rightFront.setPower(1);
            drivetrain.rightRear.setPower(-1);
        }

        drivetrain.setGlobalPowers(0.0);

        panelsTelemetry.addData("leftFront", drivetrain.leftFront.getPower());
        panelsTelemetry.addData("leftRear", drivetrain.leftRear.getPower());
        panelsTelemetry.addData("rightFront", drivetrain.rightFront.getPower());
        panelsTelemetry.addData("rightRear", drivetrain.rightRear.getPower());
    }
}
