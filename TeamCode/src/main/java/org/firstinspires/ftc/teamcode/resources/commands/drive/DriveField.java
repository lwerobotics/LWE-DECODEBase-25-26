package org.firstinspires.ftc.teamcode.resources.commands.drive;

import com.seattlesolvers.solverslib.command.CommandBase;

import org.firstinspires.ftc.teamcode.resources.subsystems.drive.Drivetrain;

public class DriveField extends CommandBase {
    private Drivetrain drive;
    private double forward;
    private double strafe;
    private double turn;
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
