package org.firstinspires.ftc.teamcode.resources.subsystems;

import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.IMU;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;


public class Drivetrain extends SubsystemBase {
    private DcMotor rightFront, leftFront, rightRear, leftRear;
    private IMU imu;

    public void init(HardwareMap hMap) {
        /* motor mapping */
        rightFront = hMap.get(DcMotor.class, "rightFront");
        leftFront = hMap.get(DcMotor.class, "leftFront");
        rightRear = hMap.get(DcMotor.class, "rightRear");
        leftRear = hMap.get(DcMotor.class, "leftRear");
        /* motor directions */
        leftFront.setDirection(DcMotor.Direction.REVERSE);
        leftRear.setDirection(DcMotor.Direction.REVERSE);
        rightFront.setDirection(DcMotor.Direction.FORWARD);
        rightRear.setDirection(DcMotor.Direction.FORWARD);
        /* encoder directions */

    }
}
