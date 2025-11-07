package org.firstinspires.ftc.teamcode.resources.hardware;

import androidx.annotation.NonNull;

import com.bylazar.telemetry.TelemetryManager;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.resources.util.enums.HardwareStates;

@SuppressWarnings("FieldCanBeLocal")
public class Intake {
    private DcMotor collectionMotor;
    private Telemetry telemetry;

    /**
     * Parameters for the initialization function of the collection motor (updated 10/16/25)
     * @param hMap The hardware map used to register hardware to the robot (like motors, servos , actuators, etc.)
     */
    public void initMotor(@NonNull TelemetryManager panels, @NonNull Telemetry ftc, @NonNull HardwareMap hMap) {
        collectionMotor = hMap.get(DcMotor.class, "collectionMotor");
        collectionMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        collectionMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        collectionMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        collectionMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        /* telemetry */
        this.telemetry = ftc;
        panels.addData("Intake motor: ", HardwareStates.INITIALIZED.toString());
        ftc.addData("Intake motor: ", HardwareStates.INITIALIZED);
        panels.update();
        ftc.update();
    }

    /* The reasoning for the minimal functions here is because most of the function of the system can be done via the opmode. */
    public void in(double power) {
        collectionMotor.setPower(power);
    }

    /** @noinspection unused*/
    public void out(double power) {
        collectionMotor.setPower(-power);
    }

    public void stop() {
        collectionMotor.setPower(0.0);
    }
}