package org.firstinspires.ftc.teamcode.opmodes.testops;

import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.seattlesolvers.solverslib.command.InstantCommand;
import com.seattlesolvers.solverslib.command.ParallelCommandGroup;
import com.seattlesolvers.solverslib.command.SequentialCommandGroup;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;
import com.seattlesolvers.solverslib.gamepad.GamepadKeys;

import org.firstinspires.ftc.teamcode.resources.commands.drive.DriveField;
import org.firstinspires.ftc.teamcode.resources.commands.drive.InitDrive;
import org.firstinspires.ftc.teamcode.resources.commands.drive.SetGlobalPowers;
import org.firstinspires.ftc.teamcode.resources.commands.intake.InitIntake;
import org.firstinspires.ftc.teamcode.resources.commands.intake.IntakeArtifact;
import org.firstinspires.ftc.teamcode.resources.commands.outtake.InitOuttake;
import org.firstinspires.ftc.teamcode.resources.commands.outtake.OuttakeArtifact;
import org.firstinspires.ftc.teamcode.resources.subsystems.drive.Drivetrain;
import org.firstinspires.ftc.teamcode.resources.subsystems.intake.Collection;
import org.firstinspires.ftc.teamcode.resources.subsystems.outtake.Flywheels;
import org.firstinspires.ftc.teamcode.resources.util.FilterStickInput;
import org.firstinspires.ftc.teamcode.resources.util.IncrementPower;

@TeleOp(group = "Test OpModes", name = "DebugTeleOp")
public class DebugOp extends CommandOpMode {
    /* subsystems */
    private Drivetrain drivetrain;
    private Flywheels outtake;
    private Collection intake;
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
        intake = new Collection();
        outtake = new Flywheels();
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
                new InitOuttake(hardwareMap, outtake),
                new DriveField(drivetrain, fsi.filterStickInput(driverOp.getLeftX()), fsi.filterStickInput(driverOp.getLeftY()), fsi.filterStickInput(driverOp.getRightX()))
        ));
        /* brake button (G1) */
        driverOp.getGamepadButton(GamepadKeys.Button.X)
                .whenActive(
                        new SetGlobalPowers(drivetrain, 0.0)
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
                .whenActive(
                        new IntakeArtifact(intake, intake.collectionMotor)
                );
        /* outtake op (G2) */
        toolOp.getGamepadButton(GamepadKeys.Button.Y)
                .whenActive(new ParallelCommandGroup(
                        new OuttakeArtifact(outtake, outtake.leftFlywheel, power * -1),
                        new OuttakeArtifact(outtake, outtake.rightFlywheel, power)
                ));
    }
}
