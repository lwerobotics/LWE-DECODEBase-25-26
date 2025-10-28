package org.firstinspires.ftc.teamcode.resources.commands.initializers;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.CommandBase;

import org.firstinspires.ftc.teamcode.resources.hardware.Gate;
import org.firstinspires.ftc.teamcode.resources.hardware.Outtake;

public class InitOuttake extends CommandBase {
    private Outtake outtake;
    private Gate gate;
    private HardwareMap hardware;

    /** Parameters for the initialization function of the drivetrain (updated 10/16/25)
     * @param hMap The hardware map used to register hardware to the robot (like motors, servos , actuators, etc.)
     * @param subsystem The subsystem used by the command (drivetrain, intake, outtake, etc.)
     */
    public InitOuttake(HardwareMap hMap, Outtake subsystem, Gate subsystem2) {
        this.hardware = hMap;
        this.outtake = subsystem;
        this.gate = subsystem2;
        addRequirements(subsystem);
    }

    @Override
    public void execute() {
        outtake.initOuttake(hardware);
        gate.initGate(hardware);
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
