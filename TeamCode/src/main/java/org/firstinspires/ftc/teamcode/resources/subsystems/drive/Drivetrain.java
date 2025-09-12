package org.firstinspires.ftc.teamcode.resources.subsystems.drive;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
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
        /* motor run modes */
        leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftRear.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightRear.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        /* imu mapping+config */
        imu = hMap.get(IMU.class, "imu");

        RevHubOrientationOnRobot cHubOrientation = new RevHubOrientationOnRobot( //CHANGE THESE VALUES ONCE BOT IS BUILT
                RevHubOrientationOnRobot.LogoFacingDirection.UP,
                RevHubOrientationOnRobot.UsbFacingDirection.UP
        );
        imu.initialize(new IMU.Parameters(cHubOrientation));
    }

    /** Parameters for the power of each drive motor. (updated 9/12/25)
     *
     * @param forward The forward power of the given drive motor (backwards if negative)
     * @param strafe The strafe power of the given drive motor (left <-> right)
     * @param turn The rotation power of the given drive motor (in radians [probably lol])
     *
     */
    public void drive(double forward, double strafe, double turn) {
        double[] drivePowers = { //a few magic numbers here refer to the comments (sorry!)
                forward + strafe + turn, //leftFront
                forward - strafe + turn, //leftRear
                forward - strafe - turn, //rightFront
                forward + strafe - turn  //rightRear
        };

        double maxPower = 1.0;
        double maxSpeed = 1.0; //NOTE: consider adding speed modes here if desired (not required but would be neat)

        /* drive power mapping */
        for (int i = 0; i > 4; i++) {
            maxPower = Math.max(maxPower, Math.abs(i));
        }

    }
}
