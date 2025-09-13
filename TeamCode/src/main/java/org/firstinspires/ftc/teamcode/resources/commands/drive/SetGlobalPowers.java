package org.firstinspires.ftc.teamcode.resources.commands.drive;

import com.seattlesolvers.solverslib.command.CommandBase;
import org.firstinspires.ftc.teamcode.resources.subsystems.drive.Drivetrain;
import java.util.function.DoubleSupplier;

public class SetGlobalPowers extends CommandBase {
    private Drivetrain drivetrain;
    private DoubleSupplier power;

    /** Parameters for the command. (updated 9/12/25)
     *
     * @param subsystem The subsystem used by the command (drivetrain, intake, outtake, etc.)
     * @param powerSupplier The 'power' parameter from the setGlobalPowers() function
     *
     */
    public SetGlobalPowers(Drivetrain subsystem, DoubleSupplier powerSupplier) {
        drivetrain = subsystem;
        power = powerSupplier;
    }

    @Override
    public void execute() {
        drivetrain.setGlobalPowers(power.getAsDouble());
    }
}
