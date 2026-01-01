package org.firstinspires.ftc.teamcode.resources.hardware;

import androidx.annotation.NonNull;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.resources.util.enums.HardwareStates;

public class Possession {
    public HardwareStates state = HardwareStates.NULL;
    private CRServo holderServo;

    public void initPossession(@NonNull HardwareMap hMap) {
        holderServo = hMap.get(CRServo.class, "holderServo");
        state = HardwareStates.INITIALIZED;
    }

    public void pull() {
        holderServo.setPower(-1.0);
        state = HardwareStates.ON;
    }

    public void repel() {
        holderServo.setPower(1.0);
        state = HardwareStates.REVERSE;
    }

    public void stop() {
        holderServo.setPower(0.0);
        state = HardwareStates.OFF;
    }
}
