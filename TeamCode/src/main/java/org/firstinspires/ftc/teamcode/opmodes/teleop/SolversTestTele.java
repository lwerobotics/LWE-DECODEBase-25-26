package org.firstinspires.ftc.teamcode.opmodes.teleop;

import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.seattlesolvers.solverslib.drivebase.MecanumDrive;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;
import com.seattlesolvers.solverslib.hardware.motors.Motor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

@TeleOp(group = "TeleOp", name = "CompTOp")
public class SolversTestTele extends OpMode {
    private Motor rightFront, rightRear, leftFront, leftRear;
    private MecanumDrive drivetrain;
    private Gamepad gamepad1;
    private GamepadEx driverOp;
    private GamepadEx toolOp;

    @Override
    public void init() {
        drivetrain = new MecanumDrive(leftFront, rightFront, leftRear, rightRear);
        driverOp = new GamepadEx(gamepad1);
    }

    @Override
    public void loop() {
        drivetrain.driveRobotCentric(
                driverOp.getLeftX(),
                driverOp.getLeftY(),
                driverOp.getRightY()
        );
    }
}
