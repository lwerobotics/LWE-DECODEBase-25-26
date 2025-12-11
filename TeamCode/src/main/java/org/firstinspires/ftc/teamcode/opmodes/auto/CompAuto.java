package org.firstinspires.ftc.teamcode.opmodes.auto;

import com.bylazar.telemetry.PanelsTelemetry;
import com.bylazar.telemetry.TelemetryManager;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.resources.hardware.Drivetrain;

@SuppressWarnings("FieldCanBeLocal")
@Autonomous(name = "CompAuto (Any Side)")
public class CompAuto extends LinearOpMode {
    private Drivetrain drivetrain;
    private ElapsedTime runtime;
    private SDKAutoRes res;
    private TelemetryManager panelsTelemetry;

    @Override
    public void runOpMode() throws InterruptedException {
        drivetrain = new Drivetrain();
        runtime = new ElapsedTime();
        res = new SDKAutoRes();
        panelsTelemetry = PanelsTelemetry.INSTANCE.getTelemetry();

        drivetrain.initDrivetrain(hardwareMap, panelsTelemetry, telemetry);

        waitForStart();

        drivetrain.leftFront.setPower(1);
        drivetrain.leftRear.setPower(-1);
        drivetrain.rightFront.setPower(-1);
        drivetrain.rightRear.setPower(1);

        sleep(300); //hilarious if this works

        drivetrain.setGlobalPowers(0.0);
    }
}
