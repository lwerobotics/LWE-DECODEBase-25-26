package org.firstinspires.ftc.teamcode.opmodes.teleop;

import com.bylazar.telemetry.PanelsTelemetry;
import com.bylazar.telemetry.TelemetryManager;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.resources.hardware.Drivetrain;
import org.firstinspires.ftc.teamcode.resources.hardware.Intake;
import org.firstinspires.ftc.teamcode.resources.hardware.Outtake;
import org.firstinspires.ftc.teamcode.resources.hardware.Possession;
import org.firstinspires.ftc.teamcode.resources.util.enums.GamepadConstants;
import org.firstinspires.ftc.teamcode.resources.util.enums.HardwareStates;


@TeleOp(name = "PracticeOp", group = "Full TeleOps")
@SuppressWarnings({"FieldCanBeLocal", "IfStatementWithIdenticalBranches"})
public class PracticeTeleOp extends OpMode {
    private Drivetrain drivetrain;
    private Intake intake;
    private Outtake outtake;
    private Possession possession;
    private Gamepad driverOp;
    private Gamepad toolOp;
    private TelemetryManager panelsTelemetry;
    private boolean intakeToggle, outtakeToggle, gateToggle, holderToggle = false;

    @Override
    public void init() {
        /* init */
        drivetrain = new Drivetrain();
        intake = new Intake();
        outtake = new Outtake();
        possession = new Possession();

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
        /* drive */
        drivetrain.drive(driverOp.left_stick_x, driverOp.left_stick_y, driverOp.right_stick_x * GamepadConstants.TURN_SENSITIVITY.getEnumValue()); //test this wednesday

        /* intake */
        if (toolOp.aWasPressed()) {
            intakeToggle = !intakeToggle;
        }

        if (intakeToggle == true) {
            intake.in(1.0);
        } else {
            intake.stop();
        }

        /* outtake */
        if (toolOp.yWasPressed()) {
            outtakeToggle = !outtakeToggle;
        }

        if (outtakeToggle == true) {
            outtake.on(0.55);
        } else {
            outtake.off();
        }

        /* servo control */
        if (toolOp.rightBumperWasPressed()) {
            gateToggle = !gateToggle;
        }

        if (gateToggle == true) {
            possession.allow();

            panelsTelemetry.addData("Servo: ", HardwareStates.OPEN.toString());
            telemetry.addData("Servo: ", HardwareStates.OPEN);
            panelsTelemetry.update();
            telemetry.update();
        } else {
            possession.block();

            panelsTelemetry.addData("Servo: ", HardwareStates.CLOSED.toString());
            telemetry.addData("Servo: ", HardwareStates.CLOSED);
            panelsTelemetry.update();
            telemetry.update();
        }

        /* ramp */
        if (toolOp.xWasPressed()) {
            holderToggle = !holderToggle;
        }

        if (holderToggle == true) {
            possession.pull();
        } else {
            possession.stop();
        }

        /* intake+possession reverse */
        if (toolOp.left_trigger > 0.65) {
            if (intakeToggle == false && holderToggle == false) {
                intake.stop();
                intake.in(-1.0);
                possession.repel();

                telemetry.addData("Reversed is: ", HardwareStates.ON);
                panelsTelemetry.addData("Reversed is: ", HardwareStates.ON.toString());
                telemetry.update();
                panelsTelemetry.update();
            }
        } else if (toolOp.left_trigger < 0.65) {
            telemetry.addData("Reversed is: ", HardwareStates.OFF);
            panelsTelemetry.addData("Reversed is: ", HardwareStates.OFF.toString());
            telemetry.update();
            panelsTelemetry.update();
        }

        /* curious experiment */
        if (driverOp.backWasReleased()) { //please logcat i need this
            System.out.println("Left flywheel velocity: " + outtake.leftFlywheel.getVelocity());
            System.out.println("Right flywheel velocity: " + outtake.rightFlywheel.getVelocity());
        }

        /* graphing */
        panelsTelemetry.addData("leftFront", drivetrain.leftFront.getPower());
        panelsTelemetry.addData("leftRear", drivetrain.leftRear.getPower());
        panelsTelemetry.addData("rightFront", drivetrain.rightFront.getPower());
        panelsTelemetry.addData("rightRear", drivetrain.rightRear.getPower());
    }
}