package org.firstinspires.ftc.teamcode.resources.commands;

import com.seattlesolvers.solverslib.command.CommandBase;
import org.firstinspires.ftc.teamcode.resources.subsystems.TSub;
public class TCommand extends CommandBase {
    private final TSub tSub;

    public TCommand(TSub subsystem) {
        tSub = subsystem;
        addRequirements(subsystem);
    }

    @Override
    public void execute() {
        //stuff here
    }
}
