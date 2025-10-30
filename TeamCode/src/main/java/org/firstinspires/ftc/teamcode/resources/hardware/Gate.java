package org.firstinspires.ftc.teamcode.resources.hardware;

import androidx.annotation.NonNull;

import com.bylazar.telemetry.TelemetryManager;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.hardware.servos.ServoEx;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.resources.util.enums.TelemetryStates;

public class Gate extends SubsystemBase {
    private ServoEx gateServo;

    public void initGate(@NonNull TelemetryManager panels, @NonNull Telemetry ftc, @NonNull HardwareMap hMap) {
        /* servo mapping+range config */
        gateServo = new ServoEx(hMap, "gateServo", 90.0, AngleUnit.RADIANS); //lowk ask vin or baron about this just to be safe
        /* telemetry */
        panels.addData("Servo: ", TelemetryStates.INITIALIZED.toString());
        ftc.addData("Servo: ", TelemetryStates.INITIALIZED);

        panels.update();
        ftc.update();
    }

    public void block() {
        gateServo.set(0.0);
    }

    public void allow() {
        gateServo.set(1.0);
    }
}