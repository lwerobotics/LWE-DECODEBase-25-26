package org.firstinspires.ftc.teamcode.resources.commands.drive;

import com.seattlesolvers.solverslib.command.CommandBase;

import org.firstinspires.ftc.teamcode.resources.hardware.Drivetrain;

public class DriveField extends CommandBase {
    private Drivetrain drive;
    private double forward;
    private double strafe;
    private double turn;

    /** Parameters for the function responsible for driving using field-based coordinates during tele-op (updated 10/6/25)
     * @param subsystem The subsystem used by the command (drivetrain, intake, outtake, etc.)
     * @param forwardPower The power of the motor(s) controlling forward drive
     * @param strafePower The power of the motor(s) controlling strafe drive
     * @param turnPower The power of the motor(s) controlling turning
     */
    public DriveField(Drivetrain subsystem, double forwardPower, double strafePower, double turnPower) {
        this.drive = subsystem;
        this.forward = forwardPower;
        this.strafe = strafePower;
        this.turn = turnPower;
        addRequirements(subsystem);
    }

    @Override
    public void execute() {
        drive.driveField(forward, strafe, turn);
    }
}
