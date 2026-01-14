package org.firstinspires.ftc.teamcode.opmodes.teleop;

import com.bylazar.configurables.annotations.Configurable;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.resources.hardware.Outtake;

@Configurable
@TeleOp(group = "Utility Tele-Ops", name = "Flywheel Tuner")
public class FlywheelTuner extends OpMode {
    public static double P = 0.0;
    public static double F = 0.0;
    private double highVel = 1200;
    private double lowVel = 900;
    private double currentTarget;
    private double[] stepSizes;
    private int index;
    private boolean toggle;
    private Outtake outtake;
    private Gamepad tunerOp;

    @Override
    public void init() {
        outtake = new Outtake();
        tunerOp = gamepad1;
        currentTarget = highVel;
        stepSizes = new double[]{10.0, 1.0, 0.1, 0.001, 0.0001};

        outtake.initOuttake(hardwareMap);
    }

    @Override
    public void loop() {
        if (tunerOp.yWasPressed()) {
            toggle = !toggle;
        }

        if (toggle == true) {
            outtake.on();
        } else {
            outtake.off();
        }

    }
}
