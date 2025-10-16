package org.firstinspires.ftc.teamcode.resources.commands.outtake;

import com.seattlesolvers.solverslib.command.InstantCommand;
import com.seattlesolvers.solverslib.hardware.motors.MotorEx;

import org.firstinspires.ftc.teamcode.resources.subsystems.outtake.Flywheels;

public class OuttakeArtifact extends InstantCommand {
    private Flywheels outtake;
    private MotorEx targetMotor;
    private double targetPower;
    private boolean functionState = false;

    /** Parameters for the constructor of this command (constructor parameters dictate what variables are exposed to the op-mode) [updated 10/16/25]
     * @param subsystem The subsystem used by the command (drivetrain, intake, outtake, etc.)
     * @param power The decimal power given to the motor (-# = reverse, +# = forward; range = [-1, 1])
     */
    public OuttakeArtifact(Flywheels subsystem, MotorEx motor, double power) {
        this.outtake = subsystem;
        this.targetMotor = motor;
        this.targetPower = power;
        addRequirements(subsystem);
    }

    @Override
    public void execute() { //aight should be good; sound logic to me
        functionState = !functionState;
        if (functionState) {
            outtake.revMotorSingular(targetMotor, targetPower);
        } else {
            outtake.revMotorSingular(targetMotor, 0.0);
        }
    }
}
