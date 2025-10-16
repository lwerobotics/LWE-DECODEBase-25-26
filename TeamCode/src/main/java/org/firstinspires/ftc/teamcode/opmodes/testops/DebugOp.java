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
import org.firstinspires.ftc.teamcode.resources.commands.outtake.RevMotorSingular;
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
                .whenActive(new InstantCommand(()
                        -> ip.incrementPower(power, false)
                ));

        toolOp.getGamepadButton(GamepadKeys.Button.DPAD_DOWN)
                .whenActive(new InstantCommand(()
                        -> ip.incrementPower(power, true)
                ));
        /* flywheel op (G2) */
        toolOp.getGamepadButton(GamepadKeys.Button.Y)
                .whenActive(new ParallelCommandGroup(
                        new RevMotorSingular(outtake, outtake.leftFlywheel, power * -1),
                        new RevMotorSingular(outtake, outtake.rightFlywheel, power)
                ));
        /* intake op (G2) */
        //continue from here, just commit the registration fix
    }
}
