package org.firstinspires.ftc.teamcode.resources.commands.drive;

import com.seattlesolvers.solverslib.command.CommandBase;

import org.firstinspires.ftc.teamcode.resources.subsystems.drive.Drivetrain;

import java.util.function.DoubleSupplier;

public class DriveField extends CommandBase {
    private Drivetrain drivetrain;
    private DoubleSupplier forward;
    private DoubleSupplier strafe;
    private DoubleSupplier turn;

    /** Parameters for the command. (updated 9/12/25)
     *
     * @param subsystem The subsystem for the given command (drivetrain, intake, outtake, etc.)
     * @param forwardSupplier The 'forward' parameter from the drive() function
     * @param strafeSupplier The 'strafe' parameter from the drive() function
     * @param turnSupplier The 'turn' parameter from the drive() function
     */
    public DriveField(Drivetrain subsystem, DoubleSupplier forwardSupplier, DoubleSupplier strafeSupplier, DoubleSupplier turnSupplier) {
        drivetrain = subsystem;
        forward = forwardSupplier;
        strafe = strafeSupplier;
        turn = turnSupplier;
    }

    @Override
    public void execute() {
        drivetrain.driveField(forward.getAsDouble(), strafe.getAsDouble(), turn.getAsDouble());
    }
}
