package org.firstinspires.ftc.teamcode.resources.commands.outtake;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.ParallelCommandGroup;
import com.seattlesolvers.solverslib.hardware.motors.MotorEx;

import org.firstinspires.ftc.teamcode.resources.subsystems.outtake.Flywheels;

public class RevFlywheels extends ParallelCommandGroup {
   public RevFlywheels(HardwareMap hMap, Flywheels subsystem, MotorEx motor1, MotorEx motor2, double power1, double power2, String name) {
      addRequirements(subsystem);
      addCommands(
              new RevMotorSingular(subsystem, motor1, hMap, power1, power2, name),
              new RevMotorSingular(subsystem, motor2, hMap, power1, power2, name)
      );
   }
}
