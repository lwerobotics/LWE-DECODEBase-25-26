package org.firstinspires.ftc.teamcode.resources.commands.intake;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.InstantCommand;
import com.seattlesolvers.solverslib.hardware.motors.MotorEx;

import org.firstinspires.ftc.teamcode.resources.subsystems.intake.Collection;

public class IntakeArtifact extends InstantCommand {
    private HardwareMap hardware;
    private Collection collection;
    private MotorEx targetMotor;
    private String motorName;
    private boolean functionState = false;

    public IntakeArtifact(HardwareMap hMap, Collection subsystem, String name) {
        this.hardware = hMap;
        this.collection = subsystem;
        this.motorName = name;
        this.targetMotor = collection.init(hMap, name);
        addRequirements(subsystem);
    }

    @Override
    public void execute() {
        functionState = !functionState;
        if (functionState) {
            collection.intake(targetMotor, 0.65); //test ts number
        } else {
            collection.intake(targetMotor, 0.0);
        }
    }
}
