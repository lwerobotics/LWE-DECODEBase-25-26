package org.firstinspires.ftc.teamcode.opmodes.auto;

import com.bylazar.telemetry.PanelsTelemetry;
import com.bylazar.telemetry.TelemetryManager;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.resources.hardware.Drivetrain;

@SuppressWarnings("FieldCanBeLocal")
@Autonomous(name = "CompAuto (Goal Side)")
public class CompAutoGoal extends LinearOpMode {
    private Drivetrain drivetrain;
    private ElapsedTime runtime;
    private SDKAutoRes res;
    private TelemetryManager panelsTelemetry;

    @Override
    public void runOpMode() throws InterruptedException {
        drivetrain = new Drivetrain();
        runtime = new ElapsedTime();
        res = new SDKAutoRes(drivetrain);
        panelsTelemetry = PanelsTelemetry.INSTANCE.getTelemetry();

        drivetrain.initDrivetrain(hardwareMap, panelsTelemetry, telemetry);

        waitForStart();

        res.forward(1);
        sleep(340); //hilarious if this works

        drivetrain.setGlobalPowers(0.0);
    }
}
