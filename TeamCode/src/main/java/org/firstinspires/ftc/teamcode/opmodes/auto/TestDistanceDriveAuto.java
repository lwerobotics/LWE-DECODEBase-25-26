package org.firstinspires.ftc.teamcode.opmodes.auto;

import com.bylazar.telemetry.PanelsTelemetry;
import com.bylazar.telemetry.TelemetryManager;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.resources.hardware.Drivetrain;
import org.firstinspires.ftc.teamcode.resources.hardware.Endgame;
import org.firstinspires.ftc.teamcode.resources.hardware.Intake;
import org.firstinspires.ftc.teamcode.resources.hardware.Outtake;
import org.firstinspires.ftc.teamcode.resources.hardware.Possession;

@SuppressWarnings("FieldCanBeLocal")
@Autonomous(name = "TEST AUTO")
public class TestDistanceDriveAuto extends LinearOpMode {
    private Drivetrain drivetrain;
    private Outtake outtake;
    private Intake intake;
    private Possession possession;
    private Endgame endgame;
    private TelemetryManager panelsTelemetry;

    @Override
    public void runOpMode() throws InterruptedException {
        /* init */
        drivetrain = new Drivetrain();
        outtake = new Outtake();
        intake = new Intake();
        possession = new Possession();
        endgame = new Endgame();
        panelsTelemetry = PanelsTelemetry.INSTANCE.getTelemetry();

        drivetrain.initDrivetrain(hardwareMap);
        outtake.initOuttake(hardwareMap);
        intake.initIntake(hardwareMap);
        possession.initPossession(hardwareMap);
        endgame.initMotors(hardwareMap);

        waitForStart();

        /* routine */
//        drivetrain.driveDistance(12);
        drivetrain.drive(1.0,0.0,1.0);
        sleep(500);
        drivetrain.setGlobalPowers(0.0);
        outtake.on(1200);
        sleep(5000);
        outtake.off();
    }
}
