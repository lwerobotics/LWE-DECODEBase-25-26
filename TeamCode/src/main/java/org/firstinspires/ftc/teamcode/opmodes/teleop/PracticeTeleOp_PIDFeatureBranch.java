package org.firstinspires.ftc.teamcode.opmodes.teleop;

import com.ThermalEquilibrium.homeostasis.Controllers.Feedback.BasicPID;
import com.ThermalEquilibrium.homeostasis.Parameters.PIDCoefficients;
import com.bylazar.configurables.annotations.Configurable;
import com.bylazar.telemetry.PanelsTelemetry;
import com.bylazar.telemetry.TelemetryManager;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
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
import org.firstinspires.ftc.teamcode.resources.util.functions.PIDController;

@Disabled
@TeleOp(name = "PID Controller TestOp", group = "Feature Branches")
@SuppressWarnings({"FieldCanBeLocal", "IfStatementWithIdenticalBranches"})
@Configurable
public class PracticeTeleOp_PIDFeatureBranch extends OpMode {
    public static double kP = 0.0;
    public static double kI = 0.0;
    public static double kD = 0.0;
    public static double reference = 0.55;
    public static double threshold = 0.55;
    private Drivetrain drivetrain;
    private Intake intake;
    private Outtake outtake;
    private Possession possession;
    private Gamepad driverOp;
    private Gamepad toolOp;
    private TelemetryManager panelsTelemetry;
    private PIDController velocityController;
    private PIDCoefficients coefficients;
    private BasicPID pidController;
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
        toolOp = gamepad2;
        panelsTelemetry = PanelsTelemetry.INSTANCE.getTelemetry();
        velocityController = new PIDController();
        coefficients = new PIDCoefficients(kP, kI, kD);
        pidController = new BasicPID(coefficients);
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
            //double power = velocityController.update(reference, outtake.leftFlywheel.getPower(), kP, kI, kD);
            double power = pidController.calculate(reference, outtake.leftFlywheel.getPower());
            outtake.on();
        } else {
            velocityController.resetController();
            outtake.off();
        }

        /* servo */
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

        /* panels graph */
        panelsTelemetry.addData("REFERENCE", reference); //please panels i need this
        panelsTelemetry.addData("POWER", outtake.rightFlywheel.getPower());
        panelsTelemetry.update();
    }
}