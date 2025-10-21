package org.firstinspires.ftc.teamcode.resources.subsystems.gate;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.hardware.servos.ServoEx;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

public class Gate extends SubsystemBase {
    public ServoEx gateServo;

    public void initGate(HardwareMap hMap) {
        /* servo mapping+range config */
        gateServo = new ServoEx(hMap, "gateServo", 90.0, AngleUnit.RADIANS); //lowk ask vin or baron about this just to be safe
    }

    public void block() {
        gateServo.set(0.0);
    }

    public void allow() {
        gateServo.set(1.0);
    }
}
