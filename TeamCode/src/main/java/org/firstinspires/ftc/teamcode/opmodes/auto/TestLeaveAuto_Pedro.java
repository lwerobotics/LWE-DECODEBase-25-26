package org.firstinspires.ftc.teamcode.opmodes.auto;

import androidx.annotation.NonNull;

import com.bylazar.configurables.annotations.Configurable;
import com.bylazar.telemetry.PanelsTelemetry;
import com.bylazar.telemetry.TelemetryManager;
import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierCurve;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathChain;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.pedroPathing.Constants;

@SuppressWarnings("FieldCanBeLocal")
@Autonomous(name = "Test Leave Auto (Pedro)")
@Configurable
public class TestLeaveAuto_Pedro extends LinearOpMode {
    /*DO NOT ATTEMPT TO RUN THIS */
    public Follower follower;
    private TelemetryManager panelsTelemetry;
    private Paths paths;
    private int state; //for the state machine

    @Override
    public void runOpMode() throws InterruptedException {
        panelsTelemetry = PanelsTelemetry.INSTANCE.getTelemetry();

        follower = Constants.createFollower(hardwareMap);
        follower.setStartingPose(new Pose(72, 8, Math.toRadians(45))); //pray
        paths = new Paths(follower);

        waitForStart();
        while (opModeIsActive()) {
            //insert logic here; read the docs but prolly tune ts first
        }
    }

    public static class Paths {
        public PathChain scorePath;
        public PathChain parkPath;

        public Paths(@NonNull Follower follower) {
            scorePath = follower
                    .pathBuilder()
                    .addPath(
                            new BezierLine(new Pose(120.000, 120.000), new Pose(78.000, 82.000))
                    )
                    .setLinearHeadingInterpolation(Math.toRadians(90), Math.toRadians(45))
                    .build();

            parkPath = follower
                    .pathBuilder()
                    .addPath(
                            new BezierCurve(
                                    new Pose(78.000, 82.000),
                                    new Pose(49.000, 103.000),
                                    new Pose(120.000, 109.000)
                            )
                    )
                    .setLinearHeadingInterpolation(Math.toRadians(90), Math.toRadians(45))
                    .build();
        }
    }
}