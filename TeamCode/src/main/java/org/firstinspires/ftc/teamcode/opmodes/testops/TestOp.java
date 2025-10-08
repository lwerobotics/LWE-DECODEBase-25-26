package org.firstinspires.ftc.teamcode.opmodes.testops;

import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.seattlesolvers.solverslib.command.InstantCommand;
import com.seattlesolvers.solverslib.command.ParallelCommandGroup;
import com.seattlesolvers.solverslib.command.SequentialCommandGroup;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;
import com.seattlesolvers.solverslib.gamepad.GamepadKeys;
import com.seattlesolvers.solverslib.hardware.motors.MotorEx;

import org.firstinspires.ftc.teamcode.resources.commands.drive.DriveField;
import org.firstinspires.ftc.teamcode.resources.commands.drive.InitDrive;
import org.firstinspires.ftc.teamcode.resources.commands.outtake.RevMotorSingular;
import org.firstinspires.ftc.teamcode.resources.subsystems.drive.Drivetrain;
import org.firstinspires.ftc.teamcode.resources.subsystems.outtake.Flywheels;
import org.firstinspires.ftc.teamcode.resources.util.FilterStickInput;
import org.firstinspires.ftc.teamcode.resources.util.IncrementPower;

@TeleOp(group = "TestOps", name = "TestTeleOp")
public class TestOp extends CommandOpMode {
    /* subsystems */
    private Drivetrain drivetrain;
    private Flywheels outtake;
    /* utilities */
    private FilterStickInput fsi;
    private IncrementPower ip; //sorry for the horrendous variable name it just looks better than 'iPower' okay :(
    /* hardware */
    private GamepadEx driverOp;
    private GamepadEx toolOp;
    /* primitives */
    private double power;

    @Override
    public void initialize() {
        /* init */
        drivetrain = new Drivetrain();
        outtake = new Flywheels();
        fsi = new FilterStickInput();
        ip = new IncrementPower();
        driverOp = new GamepadEx(gamepad1);
        toolOp = new GamepadEx(gamepad2);
        power = 0.0;
        /* drive (G1) */
        schedule(new SequentialCommandGroup(
                new InitDrive(hardwareMap, drivetrain),
                new DriveField(drivetrain, fsi.filterStickInput(driverOp.getLeftX()), fsi.filterStickInput(driverOp.getLeftY()), fsi.filterStickInput(driverOp.getRightX()))
        ));
        /* power incrementer (G2) */
        toolOp.getGamepadButton(GamepadKeys.Button.DPAD_UP)
                .whileHeld(new InstantCommand(()
                        -> ip.incrementPower(power, false)
                ));

        toolOp.getGamepadButton(GamepadKeys.Button.DPAD_DOWN)
                .whenActive(new InstantCommand(()
                        -> ip.incrementPower(power, true)
                ));
        /* flywheel op (G2) */
        toolOp.getGamepadButton(GamepadKeys.Button.Y)
                .whenActive(new ParallelCommandGroup(
                        new RevMotorSingular(hardwareMap, outtake, power, "flywheelL", true),
                        new RevMotorSingular(hardwareMap, outtake, power, "flywheelR", true)
                ));
    }
}
