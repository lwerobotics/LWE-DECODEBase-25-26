package org.firstinspires.ftc.teamcode.resources.hardware;

import androidx.annotation.NonNull;

import com.bylazar.telemetry.TelemetryManager;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.resources.util.enums.HardwareStates;

public class Possession {
    public HardwareStates servoState = HardwareStates.NULL;
    public HardwareStates holderState = HardwareStates.NULL;
    private DcMotor holderMotor;
    private Servo gateServo;

    public void initPossession(@NonNull HardwareMap hMap, @NonNull TelemetryManager panels, @NonNull Telemetry ftc) {
        /* mapping */
        holderMotor = hMap.get(DcMotor.class, "holderMotor");
        gateServo = hMap.get(Servo.class, "gateServo");
        /* config */
        holderMotor.setDirection(DcMotor.Direction.REVERSE);
        holderMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        holderMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        holderMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        /* telemetry+state */
        holderState = HardwareStates.INITIALIZED;
        servoState = HardwareStates.INITIALIZED;

        ftc.addData("Holder motor: ", HardwareStates.INITIALIZED);
        ftc.addData("Gate servo: ", HardwareStates.INITIALIZED);
        panels.addData("Holder motor: ", HardwareStates.INITIALIZED.toString());
        panels.addData("Gate servo: ", HardwareStates.INITIALIZED.toString());

        ftc.update();
        panels.update();
    }

    /* gate methods */
    public void allow() {
        gateServo.setPosition(0.9);
        servoState = HardwareStates.OPEN;
    }

    public void block() {
        gateServo.setPosition(0.0);
        servoState = HardwareStates.CLOSED;
    }

    /* holder methods */
    public void pull() {
        holderMotor.setPower(0.75);
        holderState = HardwareStates.ON;
    }

    public void repel() {
        holderMotor.setPower(-0.75);
        holderState = HardwareStates.ON;
    }

    public void stop() {
        holderMotor.setPower(0.0);
        holderState = HardwareStates.OFF;
    }
}
