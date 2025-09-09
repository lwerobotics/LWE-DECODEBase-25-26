package org.firstinspires.ftc.teamcode.resources.subsystems;

import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.IMU;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;


public class Drivetrain extends SubsystemBase {
    private final DcMotor rightFront;
    private final DcMotor leftFront;
    private final DcMotor rightRear;
    private final DcMotor leftRear;
    private IMU imu;

    public Drivetrain(DcMotor rightFront, DcMotor rightRear, DcMotor leftFront, DcMotor leftRear, IMU imu) {
     this.rightFront = rightFront;
     this.rightRear = rightRear;
     this.leftFront = leftFront;
     this.leftRear = leftRear;
     /* NOTE: make sure to add a reset motor ops before setting encoder modes later (as of 9/8/2025) */
     this.rightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
     this.rightRear.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
     this.leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
     this.leftRear.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
     /* IMU config */
     this.imu = imu; //CHANGE ORIENTATION VALUES ONCE C-HUB IS ON THE BOT (as of 9/8/2025)
     RevHubOrientationOnRobot.LogoFacingDirection logoDirection = RevHubOrientationOnRobot.LogoFacingDirection.FORWARD;
     RevHubOrientationOnRobot.UsbFacingDirection usbDirection = RevHubOrientationOnRobot.UsbFacingDirection.FORWARD;
     RevHubOrientationOnRobot robotOrientation = new RevHubOrientationOnRobot(logoDirection, usbDirection);
     imu.initialize(new IMU.Parameters(robotOrientation));
    }

    /**
     * Sets power for all drive motors at once, allows for instant brake if needed.
     * @param power - The raw motor power in percentage in decimal from 0 - 1 (Ex. 50% - 0.5)
     */
    public void setGlobalPowers(double power) {
     rightFront.setPower(power);
     leftFront.setPower(power);
     rightRear.setPower(power);
     leftRear.setPower(power);
    }

    /**
     * Sets up power settings for each wheel so that the mecanum can drive
     * @param power
     */
    public void drive(double power) {
     rightFront.setPower(power);
     leftFront.setPower(-power);
     rightRear.setPower(power);
     leftRear.setPower(-power);
    }

    public void strafe(double power) {

    }

    public void rotate(double power) {

    }
}
