package org.firstinspires.ftc.teamcode.resources.commands.outtake;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.CommandBase;
import com.seattlesolvers.solverslib.hardware.motors.MotorEx;

import org.firstinspires.ftc.teamcode.resources.subsystems.outtake.Flywheels;

public class RevMotorSingular extends CommandBase {
    private final HardwareMap hardware;
    private Flywheels outtake;
    private MotorEx targetMotor;
    private double targetPower;
    private String motorName;
    public RevMotorSingular(HardwareMap hMap, Flywheels subsystem, MotorEx motor, double power, String name) {
        this.hardware = hMap;
        this.outtake = subsystem;
        this.targetMotor = motor;
        this.targetPower = power;
        this.motorName = name;
        addRequirements(subsystem);
    }

    @Override
    public void execute() {
        outtake.init(hardware, motorName);
        outtake.revMotorSingular(targetMotor, targetPower);
    }

    @Override
    public boolean isFinished() { //ask vin about this lowk
        return true;
    }
}
