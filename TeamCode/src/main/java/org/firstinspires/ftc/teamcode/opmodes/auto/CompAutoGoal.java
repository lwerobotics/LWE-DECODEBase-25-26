package org.firstinspires.ftc.teamcode.opmodes.auto;

import com.bylazar.telemetry.PanelsTelemetry;
import com.bylazar.telemetry.TelemetryManager;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.resources.hardware.Drivetrain;
import org.firstinspires.ftc.teamcode.resources.hardware.Outtake;

@SuppressWarnings("FieldCanBeLocal")
@Autonomous(name = "CompAuto (Goal Side)")
public class CompAutoGoal extends LinearOpMode {
    private Drivetrain drivetrain;
    private Outtake outtake;
    private ElapsedTime runtime;
    private TelemetryManager panelsTelemetry;

    @Override
    public void runOpMode() throws InterruptedException {
        drivetrain = new Drivetrain();
        outtake = new Outtake();
        runtime = new ElapsedTime();
        panelsTelemetry = PanelsTelemetry.INSTANCE.getTelemetry();

        drivetrain.initDrivetrain(hardwareMap);

        waitForStart();

        drivetrain.leftFront.setPower(1.0);
        drivetrain.leftRear.setPower(-1.0);
        drivetrain.rightFront.setPower(-1.0);
        drivetrain.rightRear.setPower(1.0);

        sleep(340); //hilarious if this works

        drivetrain.setGlobalPowers(0.0);
    }
}
