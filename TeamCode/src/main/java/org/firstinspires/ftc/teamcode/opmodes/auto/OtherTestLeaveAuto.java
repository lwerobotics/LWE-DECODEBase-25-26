package org.firstinspires.ftc.teamcode.opmodes.auto;

import com.bylazar.configurables.annotations.Configurable;
import com.bylazar.telemetry.PanelsTelemetry;
import com.bylazar.telemetry.TelemetryManager;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.resources.hardware.Drivetrain;

@Configurable
@Autonomous(name = "Test Leave 2 (SDK)")
public class OtherTestLeaveAuto extends LinearOpMode {
    private Drivetrain drivetrain;
    private ElapsedTime runtime;
    private SDKAutoRes res;
    private TelemetryManager panelsTelemetry;

    public static int ms = 100;

    @Override
    public void runOpMode() throws InterruptedException {
        drivetrain = new Drivetrain();
        runtime = new ElapsedTime();
        res = new SDKAutoRes();
        panelsTelemetry = PanelsTelemetry.INSTANCE.getTelemetry();

        drivetrain.initDrivetrain(hardwareMap, panelsTelemetry, telemetry);

        waitForStart();

        drivetrain.leftFront.setPower(1);
        drivetrain.leftRear.setPower(-1);
        drivetrain.rightFront.setPower(-1);
        drivetrain.rightRear.setPower(1);

        sleep(ms); //hilarious if this works

        drivetrain.setGlobalPowers(0.0);
    }
}
