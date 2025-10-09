package org.firstinspires.ftc.teamcode.resources.commands.outtake;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.InstantCommand;
import com.seattlesolvers.solverslib.hardware.motors.MotorEx;

import org.firstinspires.ftc.teamcode.resources.subsystems.outtake.Flywheels;

public class RevMotorSingular extends InstantCommand {
    private final HardwareMap hardware;
    private Flywheels outtake;
    private MotorEx targetMotor;
    private String motorName;
    private double targetPower;
    private boolean functionState;

    /** Parameters for the constructor of this command (constructor parameters dictate what variables are exposed to the op-mode) [updated 10/8/25]
     * @param hMap
     * @param subsystem
     * @param power
     * @param name
     * @param state
     */
    public RevMotorSingular(HardwareMap hMap, Flywheels subsystem, double power, String name, boolean state) {
        this.hardware = hMap;
        this.outtake = subsystem;
        this.targetPower = power;
        this.motorName = name;
        this.targetMotor = outtake.init(hardware, motorName);
        this.functionState = state;
        addRequirements(subsystem);
    }

    @Override
    public void execute() {
       if (functionState == true) {
           outtake.revMotorSingular(targetMotor, targetPower);
       } else {
           outtake.revMotorSingular(targetMotor, 0.0);
       }
    }
}
