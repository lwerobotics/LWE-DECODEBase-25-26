package org.firstinspires.ftc.teamcode.resources.commands.intake;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.CommandBase;

import org.firstinspires.ftc.teamcode.resources.subsystems.intake.Collection;

public class InitIntake extends CommandBase {
    private Collection intake;
    private HardwareMap hardware;

    public InitIntake(HardwareMap hMap, Collection subsystem) {
        this.hardware = hMap;
        this.intake = subsystem;
        addRequirements(subsystem);
    }

    @Override
    public void execute() {
        intake.init(hardware);
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
