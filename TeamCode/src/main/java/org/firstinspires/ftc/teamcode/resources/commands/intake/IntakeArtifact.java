package org.firstinspires.ftc.teamcode.resources.commands.intake;

import com.seattlesolvers.solverslib.command.InstantCommand;
import com.seattlesolvers.solverslib.hardware.motors.MotorEx;

import org.firstinspires.ftc.teamcode.resources.subsystems.intake.Collection;

public class IntakeArtifact extends InstantCommand {
    private Collection collection;
    private MotorEx targetMotor;
    private boolean functionState = false;

    public IntakeArtifact(Collection subsystem, MotorEx motor) {
        this.collection = subsystem;
        this.targetMotor = motor;
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
