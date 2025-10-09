package org.firstinspires.ftc.teamcode.resources.commands.intake;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.InstantCommand;
import com.seattlesolvers.solverslib.hardware.motors.MotorEx;

import org.firstinspires.ftc.teamcode.resources.subsystems.outtake.Flywheels;

public class IntakeArtifact extends InstantCommand {
    private final HardwareMap hardware;
    private Flywheels outtake;
    private MotorEx targetMotor;
    private String motorName;
    private double targetPower;

    public IntakeArtifact() {
        //ignore the warning here work on this tmrw or just give it a rest tbh, have the other guys read up and then look over this code so far before testing
        //DONT SOLO THIS SHIT MAN IT AINT WORTH IT...
    }

    @Override
    public void initialize() {

    }
}
