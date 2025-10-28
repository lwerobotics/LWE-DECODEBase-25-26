package org.firstinspires.ftc.teamcode.opmodes.testops;

import com.bylazar.telemetry.PanelsTelemetry;
import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.seattlesolvers.solverslib.command.InstantCommand;
import com.seattlesolvers.solverslib.command.ParallelCommandGroup;
import com.seattlesolvers.solverslib.command.UninterruptibleCommand;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;
import com.seattlesolvers.solverslib.gamepad.GamepadKeys;

import org.firstinspires.ftc.teamcode.resources.commands.drive.DriveField;
import org.firstinspires.ftc.teamcode.resources.commands.initializers.InitDrive;
import org.firstinspires.ftc.teamcode.resources.commands.initializers.InitIntake;
import org.firstinspires.ftc.teamcode.resources.commands.initializers.InitOuttake;
import org.firstinspires.ftc.teamcode.resources.subsystems.drive.Drivetrain;
import org.firstinspires.ftc.teamcode.resources.subsystems.gate.Gate;
import org.firstinspires.ftc.teamcode.resources.subsystems.intake.Intake;
import org.firstinspires.ftc.teamcode.resources.subsystems.outtake.Outtake;
import org.firstinspires.ftc.teamcode.resources.util.functions.FilterStickInput;
import org.firstinspires.ftc.teamcode.resources.util.enums.GamepadConstants;
import org.firstinspires.ftc.teamcode.resources.util.functions.IncrementPower;
import org.firstinspires.ftc.teamcode.resources.util.functions.TelemetryTest;

@TeleOp(group = "Test OpModes", name = "DebugTeleOp")
public class DebugOp extends CommandOpMode {
    /* subsystems */
    private Drivetrain drivetrain;
    private Outtake outtake;
    private Intake intake;
    private Gate gate;
    /* hardware */
    private GamepadEx driverOp;
    private GamepadEx toolOp;
    /* utilities */
    private FilterStickInput fsi;
    private IncrementPower ip;
    private TelemetryTest ttest;
    /* miscellaneous */
    private double power;
    private PanelsTelemetry pTelemetry;

    /** NOTE: (updated 10/23/25)
     * Although the function says "initialize()", it does not exactly operate like an initializer in the sense that it initializes things to be used later,
     * rather it is a general initialzer of the opmode as a whole (as seen below, sorry if thats too obvious lol)
     */

    @Override
    public void initialize() {
        /* subsystems */
        drivetrain = new Drivetrain();
        intake = new Intake();
        outtake = new Outtake();
        gate = new Gate();
        /* utilities */
        fsi = new FilterStickInput();
        ip = new IncrementPower();
        ttest = new TelemetryTest();
        /* gamepads */
        driverOp = new GamepadEx(gamepad1);
        toolOp = new GamepadEx(gamepad2);
        /* miscellaneous */
        power = 0.0;


        /* --GAMEPAD 1-- */


        /* drive */
        schedule(new ParallelCommandGroup(
                new InitDrive(hardwareMap, drivetrain),
                new InitIntake(hardwareMap, intake),
                new InitOuttake(hardwareMap, outtake, gate),
                new UninterruptibleCommand(
                        new DriveField( //i honestly dont know why i have to multiply the params by something to get the param names in android studio dont ask
                                drivetrain,
                                fsi.filterStickInput(driverOp.getLeftX() ) * 1,
                                fsi.filterStickInput(driverOp.getLeftY()) * 1,
                                fsi.filterStickInput(driverOp.getRightX()) * GamepadConstants.TURN_SENSITIVITY.getEnumValue()))
        ));

        /* brake button */
        driverOp.getGamepadButton(GamepadKeys.Button.X)
                .whenActive(new InstantCommand(()
                        -> drivetrain.setGlobalPowers(0.0))
                );


        /* --GAMEPAD 2-- */


        /* power incrementer */
        toolOp.getGamepadButton(GamepadKeys.Button.DPAD_UP)
                .whenPressed(new InstantCommand(()
                        -> ip.incrementPower(power, false) //test this
                ));

        toolOp.getGamepadButton(GamepadKeys.Button.DPAD_DOWN)
                .whenPressed(new InstantCommand(()
                        -> ip.incrementPower(power, true)
                ));

        /* telemetry test */
        toolOp.getGamepadButton(GamepadKeys.Button.X)
                .whenPressed(new InstantCommand(() //please ask baron or someone on this; it feels wrong man (10/27/25)
                        -> ttest.telemetryTest(pTelemetry, telemetry))
                );

        /* intake op */
        toolOp.getGamepadButton(GamepadKeys.Button.A)
                .toggleWhenPressed(new InstantCommand(()
                        -> intake.in(power)), new InstantCommand(()
                        -> intake.stop())
                );
        /* outtake op */
        toolOp.getGamepadButton(GamepadKeys.Button.Y)
                .toggleWhenPressed(new InstantCommand(()
                        -> outtake.on(power)), new InstantCommand(()
                        -> outtake.off())
                );
        /* gate op */
        toolOp.getGamepadButton(GamepadKeys.Button.RIGHT_BUMPER)
                .toggleWhenPressed(new InstantCommand(()
                        -> gate.allow()), new InstantCommand(()
                        -> gate.block())
                );

        /* */
    }
}