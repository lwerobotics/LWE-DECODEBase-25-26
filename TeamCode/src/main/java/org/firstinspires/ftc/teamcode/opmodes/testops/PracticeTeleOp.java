package org.firstinspires.ftc.teamcode.opmodes.testops;

import com.bylazar.telemetry.PanelsTelemetry;
import com.bylazar.telemetry.TelemetryManager;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.resources.hardware.Drivetrain;
import org.firstinspires.ftc.teamcode.resources.hardware.Intake;
import org.firstinspires.ftc.teamcode.resources.hardware.Outtake;
import org.firstinspires.ftc.teamcode.resources.hardware.Possession;

@TeleOp(name = "PracticeOp")
public class PracticeTeleOp extends OpMode {
    private Drivetrain drivetrain;
    private Intake intake;
    private Outtake outtake;
    private Possession possession;
    private Gamepad driverOp;
    private Gamepad toolOp;
    private TelemetryManager panelsTelemetry;

    @Override
    public void init() {
        /* init */
        drivetrain = new Drivetrain();
        intake = new Intake();
        outtake = new Outtake();

        driverOp = gamepad1;
        toolOp = gamepad2;
        panelsTelemetry = PanelsTelemetry.INSTANCE.getTelemetry();

        intake.initMotor(panelsTelemetry, telemetry, hardwareMap);
        outtake.initOuttake(panelsTelemetry, telemetry, hardwareMap);
        possession.initPossession(hardwareMap, panelsTelemetry, telemetry);
        drivetrain.initDrivetrain(hardwareMap, panelsTelemetry, telemetry);
    }

    @Override
    public void loop() {
        drivetrain.driveField(driverOp.left_stick_x, driverOp.left_stick_y, driverOp.right_stick_x);

        /* intake */
        if (toolOp.aWasPressed()) {
            intake.in(1.0);
        } else if (toolOp.bWasPressed()) {
            intake.stop();
        }

        /* outtake */
        //continue tmrw SPEEDRUN GO GO GO
    }
}
