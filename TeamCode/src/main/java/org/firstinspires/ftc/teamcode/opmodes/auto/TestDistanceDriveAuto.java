package org.firstinspires.ftc.teamcode.opmodes.auto;

import com.bylazar.telemetry.PanelsTelemetry;
import com.bylazar.telemetry.TelemetryManager;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.resources.hardware.Drivetrain;
import org.firstinspires.ftc.teamcode.resources.hardware.Outtake;

@SuppressWarnings("FieldCanBeLocal")
@Autonomous(name = "[TEST AUTO] Test Distance/Other Auto Items")
public class TestDistanceDriveAuto extends LinearOpMode {
    private Drivetrain drivetrain;
    private Outtake outtake;
    private TelemetryManager panelsTelemetry;

    @Override
    public void runOpMode() throws InterruptedException {
        drivetrain = new Drivetrain();
        outtake = new Outtake();
        panelsTelemetry = PanelsTelemetry.INSTANCE.getTelemetry();

        drivetrain.initDrivetrain(hardwareMap);
        outtake.initOuttake(hardwareMap);

        waitForStart();

//        drivetrain.driveDistance(12);
        drivetrain.drive(1.0,0.0,1.0);
        sleep(500);
        drivetrain.setGlobalPowers(0.0);
        outtake.on(1200);
        sleep(5000);
        outtake.off();
    }
}
