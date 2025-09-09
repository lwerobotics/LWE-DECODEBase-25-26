package org.firstinspires.ftc.teamcode.resources.commands.drive;

import com.seattlesolvers.solverslib.command.CommandBase;
import org.firstinspires.ftc.teamcode.resources.subsystems.Drivetrain;
public class SetGlobalPowers extends CommandBase {
    private final Drivetrain drivetrain;
    private final double power;

    public SetGlobalPowers(Drivetrain subsystem, double power) {
        drivetrain = subsystem;
        this.power = power;
        addRequirements(subsystem);
    }

    @Override
    public void execute() {
        drivetrain.setGlobalPowers(power);
    }
}
