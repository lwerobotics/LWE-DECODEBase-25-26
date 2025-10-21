package org.firstinspires.ftc.teamcode.resources.subsystems.gate;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.hardware.servos.ServoEx;

public class Gate extends SubsystemBase {
    public ServoEx gateServo;

    public void initGate(HardwareMap hMap) {
        /* servo mapping */
        gateServo = new ServoEx(hMap, "gateServo");
    }

    public void block() {
        //gng learn how to code servos
    }

    public void allow() {

    }
}
