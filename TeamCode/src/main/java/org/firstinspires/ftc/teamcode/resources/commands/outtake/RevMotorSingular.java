package org.firstinspires.ftc.teamcode.resources.commands.outtake;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.CommandBase;
import com.seattlesolvers.solverslib.hardware.motors.MotorEx;

import org.firstinspires.ftc.teamcode.resources.subsystems.outtake.Flywheels;

public class RevMotorSingular extends CommandBase {
    private final Flywheels outtake;
    private HardwareMap hardware;
    private MotorEx motorSingular;
    private double targetPower;
    private String targetName;
    public RevMotorSingular(Flywheels outtake, MotorEx targetMotor, HardwareMap hMap, double power, double power2, String name) {
        this.outtake = outtake;
        this.motorSingular = targetMotor;
        this.hardware = hMap;
        this.targetPower = power;
        this.targetName = name;
        addRequirements(outtake); //mere precaution
    }

    @Override
    public void execute() {
        outtake.init(hardware, targetName);
        outtake.revMotorSingular(motorSingular, targetPower);
    }
}
