package org.firstinspires.ftc.teamcode.resources.hardware;

import androidx.annotation.NonNull;

import com.bylazar.telemetry.TelemetryManager;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.hardware.motors.Motor;
import com.seattlesolvers.solverslib.hardware.motors.MotorEx;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.resources.util.enums.HardwareStates;

public class Holder extends SubsystemBase {
    public MotorEx holderMotor;
    //private HardwareStates motorState = HardwareStates.NULL;
    public void initHolder(@NonNull TelemetryManager panels, @NonNull Telemetry ftc, @NonNull HardwareMap hMap) {
        /* motor mapping+configuration */
        holderMotor = new MotorEx(hMap, "holderMotor");
        holderMotor.setRunMode(Motor.RunMode.RawPower);
        holderMotor.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        /* telemetry+state */
        panels.addData("Artifact holder motor: ", HardwareStates.INITIALIZED.toString());
        panels.addData("Artifact holder motor: ", HardwareStates.OFF.toString());
        ftc.addData("Artifact holder motor: ", HardwareStates.INITIALIZED);
        ftc.addData("Artifact holder motor: ", HardwareStates.OFF);
        panels.update();
        ftc.update();

        //motorState = HardwareStates.OFF;
    }

    public void powerMotor(double power) {
        holderMotor.set(power);
        //motorState = HardwareStates.ON; commented out it for v1.0b, will be reinstated at a later time (v1.0?)
    }
}
