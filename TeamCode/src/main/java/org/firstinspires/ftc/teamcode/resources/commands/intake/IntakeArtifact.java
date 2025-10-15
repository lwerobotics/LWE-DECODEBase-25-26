package org.firstinspires.ftc.teamcode.resources.commands.intake;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.InstantCommand;
import com.seattlesolvers.solverslib.hardware.motors.MotorEx;

import org.firstinspires.ftc.teamcode.resources.subsystems.outtake.Flywheels;

public class IntakeArtifact extends InstantCommand {
    private HardwareMap hardware;
    private Flywheels intake;
    private MotorEx targetMotor;
    private String motorName;
    private double targetPower;

    public IntakeArtifact(HardwareMap hMap, Flywheels subsystem, String name, double power) {
        this.hardware = hMap;
        this.intake = subsystem;
        this.motorName = name;
        this.targetPower = power;
        this.targetMotor = intake.init(hMap, name);
        addRequirements(subsystem);
    }

    @Override
    public void initialize() {
        
    }
}
