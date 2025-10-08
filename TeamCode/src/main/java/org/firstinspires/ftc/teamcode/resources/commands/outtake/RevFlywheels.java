package org.firstinspires.ftc.teamcode.resources.commands.outtake;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.ParallelCommandGroup;
import com.seattlesolvers.solverslib.hardware.motors.MotorEx;

import org.firstinspires.ftc.teamcode.resources.subsystems.outtake.Flywheels;

public class RevFlywheels extends ParallelCommandGroup {
    public RevFlywheels(HardwareMap hMap, Flywheels outtake, MotorEx targetMotor1, MotorEx targetMotor2, double motorPower, String motorName1, String motorName2) {
        addCommands(
                new RevMotorSingular(hMap, outtake, targetMotor1, motorPower, motorName1),
                new RevMotorSingular(hMap, outtake, targetMotor2, motorPower, motorName2)
        );
        addRequirements(outtake);
    }
}
