package org.firstinspires.ftc.teamcode.opmodes.testops;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.seattlesolvers.solverslib.command.CommandOpMode;
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

@TeleOp(group = "", name = "Practice/Test TeleOp")
public class PracticeTeleOp extends CommandOpMode {
    /* subsystems */
    private Drivetrain drivetrain;
    private Intake intake;
    private Outtake outtake;
    private Gate gate;
    /* utilities */
    private FilterStickInput fsi;
    /* hardware */
    private GamepadEx driverOp;
    private GamepadEx toolOp;
    /* primitives */
    private double power;

    /** NOTICE:
     *  THIS OPMODE SHALL BE USED BY THE PROGRAMMING TEAM TO MESS AROUND WITH SO THAT WE CAN USE THIS DURING PRACTICE
     *  BETWEEN COMPS AND THEN ONCE WE HAVE GOOD CODE PUSH THIS TO THE COMPETITION TELEOP (DENOTED IN THE ANNOTATION
     *  ABOVE THE CLASS) SO THAT WE DONT RUN INTO ANY SHENANIGANS DURING COMP (HAPPENED A BIT LAST YEAR WAS LOWK QUITE
     *  BUNS)
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
        /* gamepads */
        driverOp = new GamepadEx(gamepad1);
        toolOp = new GamepadEx(gamepad2);
        /* primitives */
        power = 0.0;

        /* drive (G1) */
        schedule(new ParallelCommandGroup(
                new InitIntake(hardwareMap, intake),
                new InitOuttake(hardwareMap, outtake, gate),
                new InitDrive(hardwareMap, drivetrain),
                new UninterruptibleCommand(new DriveField(drivetrain, fsi.filterStickInput(driverOp.getLeftX()), fsi.filterStickInput(driverOp.getLeftY()), fsi.filterStickInput(driverOp.getRightX())))
        ));
        /* brake button (G1) */
        driverOp.getGamepadButton(GamepadKeys.Button.X)
                .whenActive(new InstantCommand(()
                        -> drivetrain.setGlobalPowers(0.0))
                );

        /* intake op (G2) */
        toolOp.getGamepadButton(GamepadKeys.Button.A)
                .toggleWhenPressed(new InstantCommand(() //use debugOp to pop in proper number
                        -> intake.in(0.5)), new InstantCommand(()
                        -> intake.stop())
                );
        /* outtake op (G2) */
        toolOp.getGamepadButton(GamepadKeys.Button.Y)
                .toggleWhenPressed(new InstantCommand(() //use debugOp to pop in proper number
                        -> outtake.on(0.67)), new InstantCommand(()
                        -> outtake.off())
                );
        /* gate op (G2) */
        toolOp.getGamepadButton(GamepadKeys.Button.RIGHT_BUMPER).toggleWhenPressed(new InstantCommand(()
                -> gate.allow()), new InstantCommand(()
                -> gate.block())
        );
    }
}