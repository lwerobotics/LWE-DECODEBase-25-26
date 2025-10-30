package org.firstinspires.ftc.teamcode.resources.hardware;

import androidx.annotation.NonNull;

import com.bylazar.telemetry.TelemetryManager;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.hardware.motors.Motor;
import com.seattlesolvers.solverslib.hardware.motors.MotorEx;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.resources.util.enums.TelemetryStates;

public class Holder extends SubsystemBase {
    public MotorEx holderMotor;
    public void initHolder(@NonNull TelemetryManager panels, @NonNull Telemetry ftc, @NonNull HardwareMap hMap) {
        /* motor mapping+configuration */
        holderMotor = new MotorEx(hMap, "holderMotor");
        holderMotor.setRunMode(Motor.RunMode.RawPower);
        holderMotor.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        /* telemetry */
        panels.addData("Artifact holder motor: ", TelemetryStates.INITIALIZED.toString());
        ftc.addData("Artifact holder motor: ", TelemetryStates.INITIALIZED);
        panels.update();
        ftc.update();
    }

    public void powerMotor(double power) {
        holderMotor.set(power);
    }
}
