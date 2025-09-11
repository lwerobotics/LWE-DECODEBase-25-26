package org.firstinspires.ftc.teamcode.resources.subsystems.drive;

import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.IMU;

/* I MAY END UP USING SOLVERSLIB MECANUM IM GONNA MAKE TWO TELES WITH THIS MECANUM CODE AND SOLVERS MECANUM AND SEE WHICH ONE I LIKE BETTER <3 */
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
