package org.firstinspires.ftc.teamcode.opmodes.auto;

import org.firstinspires.ftc.teamcode.resources.hardware.Drivetrain;

public class SDKAutoRes extends TestLeaveAuto {
    private final Drivetrain drivetrain = new Drivetrain();
    public SDKAutoRes() {}

    public void forward(double multiplier) {
        drivetrain.leftFront.setPower(1*multiplier);
        drivetrain.leftRear.setPower(-1*multiplier);
        drivetrain.rightFront.setPower(-1*multiplier);
        drivetrain.rightRear.setPower(1*multiplier);
    }
    public void backward(double multiplier) {
        drivetrain.leftFront.setPower(-1*multiplier);
        drivetrain.leftRear.setPower(1*multiplier);
        drivetrain.rightFront.setPower(1*multiplier);
        drivetrain.rightRear.setPower(-1*multiplier);
    }
    public void leftStrafe(double multiplier) {
        drivetrain.leftFront.setPower(-1*multiplier);
        drivetrain.leftRear.setPower(-1*multiplier);
        drivetrain.rightFront.setPower(-1*multiplier);
        drivetrain.rightRear.setPower(-1*multiplier);
    }
    public void rightStrafe(double multiplier) {
        drivetrain.leftFront.setPower(1*multiplier);
        drivetrain.leftRear.setPower(1*multiplier);
        drivetrain.rightFront.setPower(1*multiplier);
        drivetrain.rightRear.setPower(1*multiplier);
    }
    public void leftTurn(double multiplier) {
        drivetrain.leftFront.setPower(-1*multiplier);
        drivetrain.leftRear.setPower(1*multiplier);
        drivetrain.rightFront.setPower(-1*multiplier);
        drivetrain.rightRear.setPower(1*multiplier);
    }
    public void rightTurn(double multiplier) {
        drivetrain.leftFront.setPower(1*multiplier);
        drivetrain.leftRear.setPower(-1*multiplier);
        drivetrain.rightFront.setPower(1*multiplier);
        drivetrain.rightRear.setPower(-1*multiplier);
    }
}
