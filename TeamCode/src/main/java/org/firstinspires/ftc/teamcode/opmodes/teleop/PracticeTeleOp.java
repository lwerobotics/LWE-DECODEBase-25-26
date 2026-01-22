package org.firstinspires.ftc.teamcode.opmodes.teleop;

import com.bylazar.telemetry.PanelsTelemetry;
import com.bylazar.telemetry.TelemetryManager;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.resources.hardware.Drivetrain;
import org.firstinspires.ftc.teamcode.resources.hardware.Endgame;
import org.firstinspires.ftc.teamcode.resources.hardware.Intake;
import org.firstinspires.ftc.teamcode.resources.hardware.Outtake;
import org.firstinspires.ftc.teamcode.resources.hardware.Possession;
import org.firstinspires.ftc.teamcode.resources.util.enums.GamepadConstants;
import org.firstinspires.ftc.teamcode.resources.util.enums.HardwareStates;
import org.firstinspires.ftc.teamcode.resources.util.functions.FilterStickInput;


@TeleOp(name = "PracticeOp", group = "In-dev TeleOps")
@SuppressWarnings({"FieldCanBeLocal", "FieldMayBeFinal"})
public class PracticeTeleOp extends OpMode {
    private Drivetrain drivetrain;
    private Intake intake;
    private Outtake outtake;
    private Possession possession;
    private Endgame endgame;
    private Gamepad driverOp;
    private Gamepad toolOp;
    private TelemetryManager panelsTelemetry;
    private FilterStickInput fsi;
    private boolean driveToggle, intakeToggle, outtakeToggle, holderToggle, endgameToggle, endgameReverseToggle = false;
    private double driveMode = GamepadConstants.NORMAL.getEnumValue();
    private double turnMode = GamepadConstants.TURN_SENSITIVITY.getEnumValue();
    private HardwareStates reverseRampState = HardwareStates.NULL;
    private HardwareStates manualPwrControlState = HardwareStates.NULL;

    @Override
    public void init() {
        /* init */
        drivetrain = new Drivetrain();
        intake = new Intake();
        outtake = new Outtake();
        possession = new Possession();
        endgame = new Endgame();

        driverOp = gamepad1;
        toolOp = gamepad2;
        panelsTelemetry = PanelsTelemetry.INSTANCE.getTelemetry();
        fsi = new FilterStickInput();

        intake.initIntake(hardwareMap);
        outtake.initOuttake(hardwareMap);
        possession.initPossession(hardwareMap);
        drivetrain.initDrivetrain(hardwareMap);
        endgame.initMotors(hardwareMap);
    }

    @Override
    public void loop() {
        /* drive */
        drivetrain.drive(
                fsi.filterStickInput(driverOp.left_stick_x) * driveMode,
                fsi.filterStickInput(driverOp.left_stick_y) * driveMode,
                fsi.filterStickInput(-driverOp.right_stick_x) * turnMode);

        /* gearshift */
        if (driverOp.xWasPressed()) {
            driveMode = GamepadConstants.NORMAL.getEnumValue();
            turnMode = GamepadConstants.TURN_SENSITIVITY.getEnumValue();
        }

        if (driverOp.aWasPressed()) {
            driveMode = GamepadConstants.SLOW.getEnumValue();
            turnMode = GamepadConstants.SLOW_TURN.getEnumValue();
        }

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
            outtake.on();
        } else {
            outtake.off();
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

        /* endgame (TEST TS) */
        if (toolOp.leftBumperWasPressed()) {
            endgameToggle = !endgameToggle;
        }

        if (toolOp.rightBumperWasPressed()) {
            endgameReverseToggle = !endgameReverseToggle;
        }

        if (endgameToggle == true) {
            endgame.extend();
        } else {
            endgame.brake();
        }

        if (endgameReverseToggle == true) {
            endgame.retract();
        } else {
            endgame.brake();
        }

        /* intake+possession reverse */
        if (toolOp.left_trigger > 0.65) {
            if (intakeToggle == false && holderToggle == false) {
                intake.stop();
                intake.out(1.0);
                possession.repel();
                reverseRampState = HardwareStates.ON;
            }
        } else if (toolOp.left_trigger < 0.65) {
            reverseRampState = HardwareStates.OFF;
        }

        /* flywheel power incrementer (REPLACE WITH PIDF SOON AS POSSIBLE!!!!) */
        if (driverOp.dpadUpWasPressed()) {
            if (outtake.power >= 0.0 && outtake.power < 1.0) {
                outtake.power = outtake.power + 0.01;
            } else if (outtake.power == 1.0) {
                System.out.println("bleh");
            }
        } else if (driverOp.dpadDownWasPressed()) {
            if (outtake.power > 0.0 && outtake.power <= 1.0) {
                outtake.power = outtake.power - 0.01;
            } else if (outtake.power == 0.0) {
                System.out.println("blah");
            }
        }

        /* the silly */
        if (driverOp.right_trigger > 0.65) {
            if (intakeToggle == false && holderToggle == false) {
                intake.stop();
                intake.in(1.0);
                possession.pull();
            }
        } else if (driverOp.right_trigger < 0.65) {
            System.out.println("bluh");
        }

        /* to get a name or to not get a name that is the question */
        String modename = "NULL";
        if (driveMode == 1) {
            modename = "NORMAL";
        } else if (driveMode == 0.50) {
            modename = "PRECISION";
        }

        /* telemetry block */
        telemetry.addLine("-----===DRIVE STATUSES===-----");
        telemetry.addLine("Drive mode: "+modename);
        telemetry.addLine("-----===HARDWARE STATUSES===-----");
        telemetry.addData("Drivetrain: ", drivetrain.state);
        telemetry.addData("Intake: ", intake.state);
        telemetry.addData("Outtake: ", outtake.state);
        telemetry.addData("Ramp: ", possession.state);
        telemetry.addData("Slides: ", endgame.state);
        telemetry.addLine("-----===POWER LEVELS===-----");
        telemetry.addData("Outtake power: ", (outtake.power)*100+"%");
        telemetry.addData("Outtake velocity: ", outtake.leftFlywheel.getVelocity());
        telemetry.addLine("-----===UTILITY STATUSES===-----");
        telemetry.addData("Ramp reverse: ", reverseRampState);
        telemetry.addData("Manual flywheel control: ", manualPwrControlState);
        //ensure panels actually shows the stringed version lol
        panelsTelemetry.addLine("-----===HARDWARE STATUSES===-----");
        panelsTelemetry.addData("Drivetrain: ", drivetrain.state);
        panelsTelemetry.addData("Intake: ", intake.state);
        panelsTelemetry.addData("Outtake: ", outtake.state);
        panelsTelemetry.addData("Ramp: ", possession.state);
        panelsTelemetry.addData("Slides: ", endgame.state);
        panelsTelemetry.addLine("-----===POWER LEVELS===-----");
        panelsTelemetry.addData("Outtake power: ", (outtake.power)*100);
        panelsTelemetry.addLine("-----===UTILITY STATUSES===-----");
        panelsTelemetry.addData("Ramp reverse: ", reverseRampState);
        panelsTelemetry.addData("Manual flywheel control: ", manualPwrControlState);

        telemetry.update();
        panelsTelemetry.update();

        /* graphing */
        panelsTelemetry.addData("leftFront", drivetrain.leftFront.getPower());
        panelsTelemetry.addData("leftRear", drivetrain.leftRear.getPower());
        panelsTelemetry.addData("rightFront", drivetrain.rightFront.getPower());
        panelsTelemetry.addData("rightRear", drivetrain.rightRear.getPower());
        panelsTelemetry.addData("flywheel", outtake.rightFlywheel.getPower());
    }
}