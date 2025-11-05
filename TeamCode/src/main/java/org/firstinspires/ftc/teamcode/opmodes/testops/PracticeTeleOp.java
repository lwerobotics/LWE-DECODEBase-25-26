package org.firstinspires.ftc.teamcode.opmodes.testops;

import com.bylazar.telemetry.PanelsTelemetry;
import com.bylazar.telemetry.TelemetryManager;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.seattlesolvers.solverslib.command.CommandOpMode;
//import com.seattlesolvers.solverslib.command.CommandScheduler;
import com.seattlesolvers.solverslib.command.InstantCommand;
import com.seattlesolvers.solverslib.command.SequentialCommandGroup;
//import com.seattlesolvers.solverslib.command.UninterruptibleCommand;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;
import com.seattlesolvers.solverslib.gamepad.GamepadKeys;

import org.firstinspires.ftc.teamcode.resources.commands.drive.DriveField;
import org.firstinspires.ftc.teamcode.resources.commands.initializers.InitDrive;
import org.firstinspires.ftc.teamcode.resources.commands.initializers.InitIntake;
import org.firstinspires.ftc.teamcode.resources.commands.initializers.InitOuttake;
import org.firstinspires.ftc.teamcode.resources.commands.utility.KillRobot;
import org.firstinspires.ftc.teamcode.resources.hardware.Drivetrain;
import org.firstinspires.ftc.teamcode.resources.hardware.Gate;
import org.firstinspires.ftc.teamcode.resources.hardware.Holder;
import org.firstinspires.ftc.teamcode.resources.hardware.Intake;
import org.firstinspires.ftc.teamcode.resources.hardware.Outtake;
//import org.firstinspires.ftc.teamcode.resources.util.enums.GamepadConstants;
import org.firstinspires.ftc.teamcode.resources.util.functions.FilterStickInput;

@TeleOp(name = "PracticeOp")
public class PracticeTeleOp extends CommandOpMode {
    /* subsystems */
    private Drivetrain drivetrain;
    private Intake intake;
    private Outtake outtake;
    private Gate gate;
    private Holder holder;
    /* utilities */
    /** @noinspection FieldCanBeLocal*/
    /* these no-inspections here are meant to suppress warnings about how these field variables can be local,
     * although they CAN be, it isn't going to break anything if they arent and are initialized at the start
     * of the opmode. */
    private FilterStickInput fsi;
    /* hardware */
    /** @noinspection FieldCanBeLocal*/
    private GamepadEx driverOp;
    /** @noinspection FieldCanBeLocal*/
    private GamepadEx toolOp;
    /* miscellaneous */
    /** @noinspection FieldCanBeLocal*/
    private TelemetryManager panelsTelemetry;

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
        holder = new Holder();
        /* utilities */
        fsi = new FilterStickInput();
        /* gamepads */
        driverOp = new GamepadEx(gamepad1);
        toolOp = new GamepadEx(gamepad2);
        /* miscellaneous */
        panelsTelemetry = PanelsTelemetry.INSTANCE.getTelemetry();


        /* --GAMEPAD 1-- */


        /* drive */
        schedule(new SequentialCommandGroup(
                new InitDrive(panelsTelemetry, telemetry, hardwareMap, drivetrain),
                new InitIntake(panelsTelemetry, telemetry, hardwareMap, intake),
                new InitOuttake(panelsTelemetry, telemetry, hardwareMap, outtake, gate, holder),
                new DriveField(drivetrain,
                        fsi.filterStickInput(driverOp.gamepad.left_stick_x) * 1,
                        fsi.filterStickInput(driverOp.gamepad.left_stick_y) * 1,
                        fsi.filterStickInput(driverOp.gamepad.right_stick_x) * 1)
        ));
        /* brake button */
        driverOp.getGamepadButton(GamepadKeys.Button.X)
                .whenActive(new InstantCommand(()
                        -> drivetrain.setGlobalPowers(1.0)) //test purposes
                );
        /* kill switch G1 */
        driverOp.getGamepadButton(GamepadKeys.Button.BACK)
                .whenActive(new KillRobot(
                        drivetrain, intake, outtake, gate, telemetry
                ));


        /* --GAMEPAD 2-- */


        /* intake op */
        toolOp.getGamepadButton(GamepadKeys.Button.A)
                .toggleWhenActive(new InstantCommand(()
                        -> intake.in(1.0)), new InstantCommand(()
                        -> intake.stop())
                );
        /* outtake op */
        toolOp.getGamepadButton(GamepadKeys.Button.Y)
                .toggleWhenActive(new InstantCommand(()
                        -> outtake.on(0.67)), new InstantCommand(()
                        -> outtake.off())
                );
        /* gate op */
        toolOp.getGamepadButton(GamepadKeys.Button.RIGHT_BUMPER)
                .toggleWhenActive(new InstantCommand(()
                        -> gate.allow()), new InstantCommand(()
                        -> gate.block()));
        /* holder op */
        toolOp.getGamepadButton(GamepadKeys.Button.LEFT_BUMPER)
                .whileHeld(new InstantCommand(()
                        -> holder.powerMotor(1.0))
                );
        /* kill switch G2 */
        toolOp.getGamepadButton(GamepadKeys.Button.BACK)
                .whenPressed(new KillRobot(
                        drivetrain, intake, outtake, gate, telemetry
                ));

    }

//    @Override
//    public void run() {
//        super.run();
//        // haha vin skibidi toilet ohio
//        CommandScheduler.getInstance().run();
//    }
}