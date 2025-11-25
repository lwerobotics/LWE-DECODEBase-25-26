package org.firstinspires.ftc.teamcode.opmodes.auto;

import com.bylazar.telemetry.PanelsTelemetry;
import com.bylazar.telemetry.TelemetryManager;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.resources.hardware.Drivetrain;

@Autonomous(name = "Test Leave (SDK)")
public class TestLeaveAuto extends LinearOpMode {
    private Drivetrain drivetrain;
    private ElapsedTime runtime;
    private TelemetryManager panelsTelemetry;
    private SDKAutoRes res;
    static final double GEAR_REDUCTION = 1.0; //no extra gearing
    static final double WHEEL_DIAMETER_INCHES = 4.0;
    static final double CPR = 28.0;
    static final double CPI = (CPR*GEAR_REDUCTION) / (WHEEL_DIAMETER_INCHES*3.1415);

    @Override
    public void runOpMode() throws InterruptedException {
        drivetrain = new Drivetrain();
        runtime = new ElapsedTime();
        panelsTelemetry = PanelsTelemetry.INSTANCE.getTelemetry();
        res = new SDKAutoRes();

        drivetrain.initDrivetrain(hardwareMap, panelsTelemetry, telemetry);

//        telemetry.addData("Starting at",  "%7d :%7d",
//                drivetrain.leftFront.getCurrentPosition(),
//                drivetrain.rightFront.getCurrentPosition());
//        telemetry.update();

        waitForStart();

        autoDrive(24,24,1,4.0); //forward 24 inches (IN THEORY...)
        sleep(250); //idk man
    }

    public void autoDrive(double leftInches, double rightInches, double speed, double timeout) {
        int leftTarget = 0;
        int rightTarget = 0;

        if (opModeIsActive()) {
            leftTarget = drivetrain.leftFront.getCurrentPosition() + (int)(leftInches * CPI);
            rightTarget = drivetrain.rightFront.getCurrentPosition() + (int)(rightInches * CPI);
            drivetrain.leftFront.setTargetPosition(leftTarget);
            drivetrain.rightFront.setTargetPosition(rightTarget);

            drivetrain.leftFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            drivetrain.rightFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            runtime.reset();
            drivetrain.leftFront.setPower(Math.abs(speed));
            drivetrain.rightFront.setPower(Math.abs(speed));
        }

        while (opModeIsActive() && (runtime.seconds() < timeout) && (drivetrain.leftFront.isBusy() && drivetrain.rightFront.isBusy())) {
            // Display it for the driver.
            telemetry.addData("Running to",  " %7d :%7d", leftTarget,  rightTarget);
            telemetry.addData("Currently at",  " at %7d :%7d",
                    drivetrain.leftFront.getCurrentPosition(), drivetrain.rightFront.getCurrentPosition());
            telemetry.update();
        }

        drivetrain.leftFront.setPower(0);
        drivetrain.rightFront.setPower(0);

        drivetrain.leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        drivetrain.rightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }
}
