package org.firstinspires.ftc.teamcode.resources.hardware;

import androidx.annotation.NonNull;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.resources.util.enums.HardwareStates;

@SuppressWarnings("FieldCanBeLocal")
public class Intake {
    public HardwareStates state = HardwareStates.NULL;
    private CRServo collectionServo;

    /** Parameters for the initialization of the intake hardware (updated 12/23/25)
     * @param hMap - The map for all registered hardware for the robot (motors, servos, actuators, etc.)
     */
    public void initIntake(@NonNull HardwareMap hMap) {
        collectionServo = hMap.get(CRServo.class, "collectionServo");
        state = HardwareStates.INITIALIZED;
    }


    public void in(double power) {
        collectionServo.setPower(-power);
        state = HardwareStates.ON;
    }

    /** @noinspection unused*/
    public void out(double power) {
        collectionServo.setPower(power);
        state = HardwareStates.REVERSE;
    }

    public void stop() {
        collectionServo.setPower(0.0);
        state = HardwareStates.OFF;
    }
}