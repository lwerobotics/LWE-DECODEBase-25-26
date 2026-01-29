package org.firstinspires.ftc.teamcode.resources.util.functions;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.resources.hardware.Drivetrain;
import org.firstinspires.ftc.teamcode.resources.util.enums.DriveModes;

@SuppressWarnings("FieldCanBeLocal")
@TeleOp(group = "Utility Tele-Ops", name = "Drive Functions TestOp")
public class TestDriveFunctions extends OpMode {
    private Drivetrain drivetrain;
    private Gamepad testerOp;
    private FilterStickInput fsi;
    private double[] modeList;
    private double currentMode;
    private int index;

    private double ms = 0.0;
    private double distanceInches = 0.0;
    private boolean goForward = true;
    private String modes = DriveModes.STANDARD.getEnumValue();

    @Override
    public void init() {
        drivetrain = new Drivetrain();
        fsi = new FilterStickInput();
        testerOp = gamepad1;
        modeList = new double[]{1.0, 2.0, 3.0}; //1 = standard, 2 = distance, 3 = time

        drivetrain.initDrivetrain(hardwareMap);
    }

    @Override
    public void loop() {
        /* gearshift */
        if (testerOp.dpadRightWasPressed()) {
            index = (index + 1) % modeList.length;
        }

        if (testerOp.dpadLeftWasPressed()) {
            index = (index - 1) % modeList.length;
        }

        /* value incrementer/decrementer */
        if (testerOp.dpadUpWasPressed()) {
            if (modeList[index] == 1) {
                distanceInches += 0.5;
            } else if (modeList[index] == 2) {
                ms += 10.0;
            }
        }

        if (testerOp.dpadDownWasPressed()) {
            if (modeList[index] == 1) {
                distanceInches -= 0.5;
            } else if (modeList[index] == 2) {
                ms -= 10.0;
            }
        }

        /* boolean switch */
        if (testerOp.aWasPressed()) {
            goForward = !goForward;
        }

        /* MODE 1 */
        if (modeList[index] == 0) {
            drivetrain.drive(
                    fsi.filterStickInput(testerOp.left_stick_x),
                    fsi.filterStickInput(testerOp.left_stick_y),
                    fsi.filterStickInput(-testerOp.right_stick_x));
        }
        /* MODE 2 */
        if (modeList[index] == 1) {
            drivetrain.driveDistance(distanceInches);
        }
        /* MODE 3 */
        if (modeList[index] == 2) {
            try {
                drivetrain.driveTime(ms, goForward);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        /* telemetry */
        telemetry.addData("Drive mode: ", modes);
        telemetry.addLine("---------------------------------");
        telemetry.addData("Inches: ", distanceInches);
        telemetry.addData("Milliseconds: ", ms);
        telemetry.addData("Go forward boolean: ", goForward);
    }
}
