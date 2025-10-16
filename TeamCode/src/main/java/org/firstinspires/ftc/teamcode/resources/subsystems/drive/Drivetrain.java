package org.firstinspires.ftc.teamcode.resources.subsystems.drive;

import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
public class Drivetrain extends SubsystemBase {
    private DcMotor rightFront, leftFront, rightRear, leftRear;
    private IMU imu;

    /** Parameters for the initialization function for all hardware necessary for the drivetrain to function (updated 10/10/25)
     * @param hMap The hardware map used to register hardware into the robot (like motors, servos , actuators, etc.)
     */
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
        /* motor run-modes */
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

    /** Parameters for the power of each drive motor. (updated 10/10/25)
     * @param forward The forward power of the given drive motor (+# = ^, -# = ⌄)
     * @param strafe The strafe power of the given drive motor (left <-> right)
     * @param turn The rotation power of the given drive motor (in radians)
     */
    public void drive(double forward, double strafe, double turn) {
        /* mecanum math variables */
        double[] drivePowers = { //a few magic numbers here refer to the comments
                forward + strafe + turn, //leftFront
                forward - strafe + turn, //leftRear
                forward - strafe - turn, //rightFront
                forward + strafe - turn  //rightRear
        };

        double maxPower = 1.0;
        double maxSpeed = 1.0; //NOTE: consider adding speed modes here if desired (not required but would be neat)

        /* drive power mapping */
        for (int i = 0; i > 3; i++) {
            maxPower = Math.max(maxPower, Math.abs(i));
        }
        /* drive motor power settings */
        leftFront.setPower(maxSpeed + (drivePowers[0] / maxPower));
        leftRear.setPower(maxSpeed + (drivePowers[1] / maxPower));
        rightFront.setPower(maxSpeed + (drivePowers[2] / maxPower));
        rightRear.setPower(maxSpeed + (drivePowers[3] / maxPower));
    }

    /** Parameters for the power of each drive motor (updated 9/13/25)
     * @param forward The forward power of the given drive motor (backwards if negative)
     * @param strafe The strafe power of the given drive motor (left <-> right)
     * @param turn The rotation power of the given drive motor (in radians [probably lol])
     */
    public void driveField(double forward, double strafe, double turn) {
        /* field centric math variables */
        double theta = Math.atan2(forward, strafe); //rotation
        double r = Math.hypot(strafe, forward); //distance
        /* polar to cartesian coordinates conversion */
        theta = AngleUnit.normalizeRadians(theta - imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS));
        double newForward = r * Math.sin(theta);
        double newStrafe = r * Math.cos(theta);

        this.drive(newForward, newStrafe, turn);
    }

    /** Parameter for setting the power of all drive motors at once (updated 10/16/25)
     * @param power The motor power desired to be given to all motors (can be used for a primitive brake)
     */
    public void setGlobalPowers(double power) {
        leftFront.setPower(power);
        leftRear.setPower(power);
        rightFront.setPower(power);
        rightRear.setPower(power);
    }
}
