package org.firstinspires.ftc.teamcode.resources.commands.drive;

import com.seattlesolvers.solverslib.command.InstantCommand;

import org.firstinspires.ftc.teamcode.resources.hardware.Drivetrain;

public class SetGlobalDrivePowers extends InstantCommand {
    private Drivetrain drivetrain;
    private double power;

    /** Parameters for the function setting a single power setting for all drive motors at once (updated 10/6/25)
     *
     * @param subsystem The subsystem used by the command (drivetrain, intake, outtake, etc.)
     * @param motorPower The power you want to set the motors to (goes from -1 -> 1 in a percentage-to-decimal format)
     *
     */
    public SetGlobalDrivePowers(Drivetrain subsystem, double motorPower) {
        drivetrain = subsystem;
        power = motorPower;
        addRequirements(subsystem);
    }

    @Override
    public void execute() {
        drivetrain.setGlobalPowers(power);
    }
}
