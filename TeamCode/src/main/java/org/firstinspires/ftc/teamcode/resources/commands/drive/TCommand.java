package org.firstinspires.ftc.teamcode.resources.commands.drive;

import com.seattlesolvers.solverslib.command.CommandBase;
import org.firstinspires.ftc.teamcode.resources.subsystems.Drivetrain;
public class TCommand extends CommandBase {
    private final Drivetrain drivetrain;

    public TCommand(Drivetrain subsystem) {
        drivetrain = subsystem;
        addRequirements(subsystem);
    }

    @Override
    public void execute() {
        //stuff here
    }
}
