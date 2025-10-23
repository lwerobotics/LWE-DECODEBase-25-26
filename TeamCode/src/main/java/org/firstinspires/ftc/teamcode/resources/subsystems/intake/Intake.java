package org.firstinspires.ftc.teamcode.resources.subsystems.intake;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.hardware.motors.Motor;
import com.seattlesolvers.solverslib.hardware.motors.MotorEx;

public class Intake extends SubsystemBase {
    public MotorEx collectionMotor;

    /**
     * Parameters for the initialization function of the collection motor (updated 10/16/25)
     * @param hMap The hardware map used to register hardware to the robot (like motors, servos , actuators, etc.)
     * @return Returns the initialized motor from the functions
     */
    public void initMotor(HardwareMap hMap) {
        /* motor mapping */
        collectionMotor = new MotorEx(hMap, "collectionMotor");
        /* motor run-mode */
        collectionMotor.setRunMode(Motor.RunMode.RawPower);
        /* motor behavior */
        collectionMotor.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
    }

    /* The reasoning for the minimal functions here is because most of the function of the system can be done via the opmode. */
    public void in(double power) {
       collectionMotor.set(power);
    }

    public void out(double power) {
        collectionMotor.set(power);
    }
    public void stop() {
        collectionMotor.stopMotor();
    }
}