package org.firstinspires.ftc.teamcode.resources.commands.initializers;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.CommandBase;

import org.firstinspires.ftc.teamcode.resources.subsystems.outtake.Outtake;

public class InitOuttake extends CommandBase {
    private Outtake outtake;
    private HardwareMap hardware;

    /** Parameters for the initialization function of the drivetrain (updated 10/16/25)
     * @param hMap The hardware map used to register hardware to the robot (like motors, servos , actuators, etc.)
     * @param subsystem The subsystem used by the command (drivetrain, intake, outtake, etc.)
     */
    public InitOuttake(HardwareMap hMap, Outtake subsystem) {
        this.hardware = hMap;
        this.outtake = subsystem;
        addRequirements(subsystem);
    }

    @Override
    public void execute() {
        outtake.initOuttake(hardware);
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
