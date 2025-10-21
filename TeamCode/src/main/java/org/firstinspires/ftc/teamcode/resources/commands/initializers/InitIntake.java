package org.firstinspires.ftc.teamcode.resources.commands.initializers;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.CommandBase;

import org.firstinspires.ftc.teamcode.resources.subsystems.intake.Intake;

public class InitIntake extends CommandBase {
    private Intake intake;
    private HardwareMap hardware;

    public InitIntake(HardwareMap hMap, Intake subsystem) {
        this.hardware = hMap;
        this.intake = subsystem;
        addRequirements(subsystem);
    }

    @Override
    public void execute() {
        intake.initMotor(hardware);
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
