package org.firstinspires.ftc.teamcode.resources.subsystems.outtake;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.hardware.motors.Motor;
import com.seattlesolvers.solverslib.hardware.motors.MotorEx;

public class Flywheels extends SubsystemBase {
    private MotorEx wheelMotor;

    public void init(HardwareMap hMap) {
        /* motor mapping */
        wheelMotor = new MotorEx(hMap, "flywheelMotor");
        /* motor run-mode */
        wheelMotor.setRunMode(Motor.RunMode.RawPower);
    }

    public void revMotorSingular(MotorEx targetMotor, double power) {
        this.wheelMotor = targetMotor;
        wheelMotor.set(power);
    }
}
