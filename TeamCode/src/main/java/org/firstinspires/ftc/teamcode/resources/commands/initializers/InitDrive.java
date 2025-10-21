package org.firstinspires.ftc.teamcode.resources.commands.initializers;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.CommandBase;

import org.firstinspires.ftc.teamcode.resources.subsystems.drive.Drivetrain;
public class InitDrive extends CommandBase {
    private Drivetrain drive;
    private HardwareMap hardware;

    /** Parameters for the initialization function of the drivetrain (updated 10/16/25)
     * @param hMap The hardware map used to register hardware to the robot (like motors, servos , actuators, etc.)
     * @param subsystem The subsystem used by the command (drivetrain, intake, outtake, etc.)
     */
    public InitDrive(HardwareMap hMap, Drivetrain subsystem) {
        this.drive = subsystem;
        this.hardware = hMap;
        addRequirements(subsystem);
    }

    @Override
    public void execute() {
        drive.initDrivetrain(hardware);
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
