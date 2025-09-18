package org.firstinspires.ftc.teamcode.resources.commands.drive;

import com.seattlesolvers.solverslib.command.CommandBase;

import org.firstinspires.ftc.teamcode.resources.subsystems.drive.Drivetrain;
import org.firstinspires.ftc.teamcode.resources.util.FilterStickInput;

import java.util.function.DoubleSupplier;

public class DriveField extends CommandBase {
    private Drivetrain drivetrain;
    private FilterStickInput fsi;
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
    public DriveField(Drivetrain subsystem, FilterStickInput util, DoubleSupplier forwardSupplier, DoubleSupplier strafeSupplier, DoubleSupplier turnSupplier) {
        drivetrain = subsystem;
        fsi = util;
        forward = forwardSupplier;
        strafe = strafeSupplier;
        turn = turnSupplier;
        addRequirements(subsystem);
    }

    @Override
    public void execute() {
        drivetrain.driveField(fsi.filterStickInput(forward.getAsDouble()), fsi.filterStickInput(strafe.getAsDouble()), fsi.filterStickInput(turn.getAsDouble()));
    }
}
