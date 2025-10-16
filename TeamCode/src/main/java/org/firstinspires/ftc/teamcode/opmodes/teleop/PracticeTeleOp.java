package org.firstinspires.ftc.teamcode.opmodes.teleop;

import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.seattlesolvers.solverslib.command.SequentialCommandGroup;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;

import org.firstinspires.ftc.teamcode.resources.commands.drive.DriveField;
import org.firstinspires.ftc.teamcode.resources.commands.drive.InitDrive;
import org.firstinspires.ftc.teamcode.resources.subsystems.drive.Drivetrain;
import org.firstinspires.ftc.teamcode.resources.subsystems.outtake.Flywheels;
import org.firstinspires.ftc.teamcode.resources.util.FilterStickInput;

public class PracticeTeleOp extends CommandOpMode {
    /* subsystems */
    private Drivetrain drivetrain;
    private Flywheels outtake;
    /* utilities */
    private FilterStickInput fsi;
    /* hardware */
    private GamepadEx driverOp;
    private GamepadEx toolOp;
    /* primitives */
    private double power;

    /* NOTICE:
     *  THIS OPMODE SHALL BE USED BY THE PROGRAMMING TEAM TO MESS AROUND WITH SO THAT WE CAN USE THIS DURING PRACTICE
     *  BETWEEN COMPS AND THEN ONCE WE HAVE GOOD CODE PUSH THIS TO THE COMPETITION TELEOP (DENOTED IN THE ANNOTATION
     *  ABOVE THE CLASS) SO THAT WE DONT RUN INTO ANY SHENANIGANS DURING COMP (HAPPENED A BIT LAST YEAR WAS LOWK QUITE
     *  BUNS)
     */

    @Override
    public void initialize() {
        /* init */
        drivetrain = new Drivetrain();
        outtake = new Flywheels();
        fsi = new FilterStickInput();
        driverOp = new GamepadEx(gamepad1);
        toolOp = new GamepadEx(gamepad2);
        power = 0.0;
        /* drive (G1) */
        schedule(new SequentialCommandGroup(
                new InitDrive(hardwareMap, drivetrain),
                new DriveField(drivetrain, fsi.filterStickInput(driverOp.getLeftX()), fsi.filterStickInput(driverOp.getLeftY()), fsi.filterStickInput(driverOp.getRightX()))
        ));
        /* intake op (G2) */
        /* outtake op (G2) */

    }
}
