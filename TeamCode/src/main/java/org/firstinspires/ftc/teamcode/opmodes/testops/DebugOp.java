package org.firstinspires.ftc.teamcode.opmodes.testops;

import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.seattlesolvers.solverslib.command.InstantCommand;
import com.seattlesolvers.solverslib.command.SequentialCommandGroup;
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
import org.firstinspires.ftc.teamcode.resources.util.FilterStickInput;
import org.firstinspires.ftc.teamcode.resources.util.GamepadConstants;
import org.firstinspires.ftc.teamcode.resources.util.IncrementPower;

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
    private IncrementPower ip; //sorry for the horrendous variable name it just looks better than 'iPower' okay :(
    /* primitives */
    private double power;

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
        /* gamepads */
        driverOp = new GamepadEx(gamepad1);
        toolOp = new GamepadEx(gamepad2);
        /* primitives */
        power = 0.0;

        /* drive (G1) */
        schedule(new SequentialCommandGroup(
                new InitDrive(hardwareMap, drivetrain),
                new InitIntake(hardwareMap, intake),
                new InitOuttake(hardwareMap, outtake, gate),
                new UninterruptibleCommand(
                        new DriveField(
                                drivetrain,
                                fsi.filterStickInput(driverOp.getLeftX()),
                                fsi.filterStickInput(driverOp.getLeftY()),
                                fsi.filterStickInput(driverOp.getRightX()) * GamepadConstants.TURN_SENSITIVITY.getEnumValue()))
        ));
        /* brake button (G1) */
        driverOp.getGamepadButton(GamepadKeys.Button.X)
                .whenActive(new InstantCommand(()
                        -> drivetrain.setGlobalPowers(0.0))
                );

        /* power incrementer (G2) */
        toolOp.getGamepadButton(GamepadKeys.Button.DPAD_UP)
                .whenActive(new InstantCommand(()
                        -> ip.incrementPower(power, false)
                ));

        toolOp.getGamepadButton(GamepadKeys.Button.DPAD_DOWN)
                .whenActive(new InstantCommand(()
                        -> ip.incrementPower(power, true)
                ));

        /* intake op (G2) */
        toolOp.getGamepadButton(GamepadKeys.Button.A)
                .toggleWhenPressed(new InstantCommand(()
                        -> intake.in(power)), new InstantCommand(()
                        -> intake.stop())
                );
        /* outtake op (G2) */
        toolOp.getGamepadButton(GamepadKeys.Button.Y)
                .toggleWhenPressed(new InstantCommand(()
                        -> outtake.on(power)), new InstantCommand(()
                        -> outtake.off())
                );
        /* gate op (G2) */
        toolOp.getGamepadButton(GamepadKeys.Button.RIGHT_BUMPER)
                .toggleWhenPressed(new InstantCommand(()
                        -> gate.allow()), new InstantCommand(()
                        -> gate.block())
                );
    }
}