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
import org.firstinspires.ftc.teamcode.resources.util.functions.FilterStickInput;


@TeleOp(name = "SoloOp", group = "Full TeleOps")
@SuppressWarnings({"FieldCanBeLocal", "IfStatementWithIdenticalBranches"})
public class SoloTeleOp extends OpMode {
    private Drivetrain drivetrain;
    private Intake intake;
    private Outtake outtake;
    private Possession possession;
    private Gamepad driverOp;
    private TelemetryManager panelsTelemetry;
    private FilterStickInput fsi;
    private boolean intakeToggle, outtakeToggle, gateToggle, holderToggle = false;

    @Override
    public void init() {
        /* init */
        drivetrain = new Drivetrain();
        intake = new Intake();
        outtake = new Outtake();
        possession = new Possession();

        driverOp = gamepad1;
        panelsTelemetry = PanelsTelemetry.INSTANCE.getTelemetry();
        fsi = new FilterStickInput();

        intake.initMotor(panelsTelemetry, telemetry, hardwareMap);
        outtake.initOuttake(panelsTelemetry, telemetry, hardwareMap);
        possession.initPossession(hardwareMap, panelsTelemetry, telemetry);
        drivetrain.initDrivetrain(hardwareMap, panelsTelemetry, telemetry);
    }

    @Override
    public void loop() {
        /* drive */
        drivetrain.drive(
                fsi.filterStickInput(driverOp.left_stick_x) * 1,
                fsi.filterStickInput(driverOp.left_stick_y) * 1,
                fsi.filterStickInput(driverOp.right_stick_x) * GamepadConstants.TURN_SENSITIVITY.getEnumValue());

        /* intake */
        if (driverOp.aWasPressed()) {
            intakeToggle = !intakeToggle;
        }

        if (intakeToggle == true) {
            intake.in(1.0);
        } else {
            intake.stop();
        }

        /* outtake */
        if (driverOp.yWasPressed()) {
            outtakeToggle = !outtakeToggle;
        }

        if (outtakeToggle == true) {
            outtake.on();
        } else {
            outtake.off();
        }

        /* servo control */
        if (driverOp.rightBumperWasPressed()) {
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
        if (driverOp.xWasPressed()) {
            holderToggle = !holderToggle;
        }

        if (holderToggle == true) {
            possession.pull();
        } else {
            possession.stop();
        }

        /* intake+possession reverse */
        if (driverOp.left_trigger > 0.65) {
            if (intakeToggle == false && holderToggle == false) {
                intake.stop();
                intake.in(-1.0);
                possession.repel();

                telemetry.addData("Reversed is: ", HardwareStates.ON);
                panelsTelemetry.addData("Reversed is: ", HardwareStates.ON.toString());
                telemetry.update();
                panelsTelemetry.update();
            }
        } else if (driverOp.left_trigger < 0.65) {
            telemetry.addData("Reversed is: ", HardwareStates.OFF);
            panelsTelemetry.addData("Reversed is: ", HardwareStates.OFF.toString());
            telemetry.update();
            panelsTelemetry.update();
        }

        /* flywheel power incrementer */
        if (driverOp.dpadUpWasPressed()) {
            if (outtake.power >= 0.0 && outtake.power <= 1.0) {
                outtake.power = outtake.power + 0.01;
            } else if (outtake.power == 1.0) {
                telemetry.addLine("Max power!!!");
                panelsTelemetry.addLine("Max power!!!");
                telemetry.update();
                panelsTelemetry.update();
            }

            telemetry.addData("Power: ", outtake.power);
            panelsTelemetry.addData("Power: ", outtake.power);
            telemetry.update();
            panelsTelemetry.update();
        } else if (driverOp.dpadDownWasPressed()) {
            if (outtake.power >= 0.0 && outtake.power <= 1.0) {
                outtake.power = outtake.power - 0.01;
            } else if (outtake.power == 0.0) {
                telemetry.addLine("Min power!!!");
                panelsTelemetry.addLine("Min power!!!");
                telemetry.update();
                panelsTelemetry.update();
            }

            telemetry.addData("Power: ", outtake.power);
            panelsTelemetry.addData("Power: ", outtake.power);
            telemetry.update();
            panelsTelemetry.update();
        }

        /* the silly */
        if (driverOp.right_trigger > 0.65) {
            if (intakeToggle == false && holderToggle == false) {
                intake.stop();
                intake.in(1.0);
                possession.pull();
            }
        } else if (driverOp.right_trigger < 0.65) {
            telemetry.addData("Forward incrementer is: ", HardwareStates.OFF);
            panelsTelemetry.addData("Forward incrementer is: ", HardwareStates.OFF.toString());
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