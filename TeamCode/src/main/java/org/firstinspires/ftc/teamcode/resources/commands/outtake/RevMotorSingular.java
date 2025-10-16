package org.firstinspires.ftc.teamcode.resources.commands.outtake;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.InstantCommand;
import com.seattlesolvers.solverslib.hardware.motors.MotorEx;

import org.firstinspires.ftc.teamcode.resources.subsystems.outtake.Flywheels;

public class RevMotorSingular extends InstantCommand {
    private Flywheels outtake;
    private MotorEx targetMotor;
    private double targetPower;
    private boolean functionState = false;

    /** Parameters for the constructor of this command (constructor parameters dictate what variables are exposed to the op-mode) [updated 10/15/25]
     * @param hMap The hardware map used to register hardware into the robot (like motors, servos , actuators, etc.)
     * @param subsystem The subsystem used by the command (drivetrain, intake, outtake, etc.)
     * @param power The decimal power given to the motor (-# = reverse, +# = forward; range = [-1, 1])
     * @param name The name given to the motor when it is registered into the hardware map (and thus the robot)
     */
    public RevMotorSingular(HardwareMap hMap, Flywheels subsystem, double power, String name) {
        this.outtake = subsystem;
        this.targetPower = power;
        this.targetMotor = outtake.init(hMap, name);
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
