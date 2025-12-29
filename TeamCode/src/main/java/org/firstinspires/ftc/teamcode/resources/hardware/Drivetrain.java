package org.firstinspires.ftc.teamcode.resources.hardware;

import androidx.annotation.NonNull;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.resources.util.enums.HardwareStates;

public class Drivetrain{
    public DcMotor rightFront, leftFront, rightRear, leftRear;
    public HardwareStates state = HardwareStates.NULL;
    private IMU imu;

    /** Parameters for the initialization function for all hardware necessary for the drivetrain to function (updated 10/10/25)
     * @param hMap The hardware map used to register hardware into the robot (like motors, servos , actuators, etc.)
     */
    public void initDrivetrain(@NonNull HardwareMap hMap) {
        /* motor mapping */
        rightFront = hMap.get(DcMotor.class, "rightFront");
        leftFront = hMap.get(DcMotor.class, "leftFront");
        rightRear = hMap.get(DcMotor.class, "rightRear");
        leftRear = hMap.get(DcMotor.class, "leftRear");
        /* motor config */
        leftFront.setDirection(DcMotor.Direction.REVERSE);
        leftRear.setDirection(DcMotor.Direction.FORWARD);
        rightFront.setDirection(DcMotor.Direction.REVERSE);
        rightRear.setDirection(DcMotor.Direction.FORWARD);

        leftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftRear.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightRear.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftRear.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightRear.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        /* after reset THEN... */
        leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftRear.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightRear.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        /* imu mapping+config */
        imu = hMap.get(IMU.class, "imu");

        RevHubOrientationOnRobot cHubOrientation = new RevHubOrientationOnRobot( //TO-DO: change for v1.1.1a
                RevHubOrientationOnRobot.LogoFacingDirection.RIGHT,
                RevHubOrientationOnRobot.UsbFacingDirection.DOWN
        );
        imu.initialize(new IMU.Parameters(cHubOrientation));
        imu.resetYaw();

        /* state control */
        state = HardwareStates.INITIALIZED;
    }

    public void setDriveMode(@NonNull HardwareMap hMap, DcMotor.RunMode mode) {
        initDrivetrain(hMap);

        leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftRear.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightRear.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        leftFront.setMode(mode);
        leftRear.setMode(mode);
        rightFront.setMode(mode);
        rightRear.setMode(mode);
    }

    /** Parameters for the power of each drive motor. (updated 10/10/25)
     * @param forward The forward power of the given drive motor (+# = ^, -# = ⌄)
     * @param strafe The strafe power of the given drive motor (left <-> right)
     * @param turn The rotation power of the given drive motor (in radians)
     */
    public void drive(double forward, double strafe, double turn) {
        /* mecanum math variables */
        double[] drivePowers = {
                forward - strafe + turn, //leftFront
                forward + strafe - turn, //leftRear
                forward + strafe + turn, //rightFront
                forward - strafe - turn  //rightRear
        };

        double maxPower = 1.0;
        double maxSpeed = 1.0; //NOTE: consider adding speed modes here if desired (not required but would be neat)

        /* drive power mapping */
        for (double power : drivePowers) {
            maxPower = Math.max(maxPower, Math.abs(power));
        }

        /* drive motor power settings */
        leftFront.setPower(maxSpeed * (drivePowers[0] / maxPower));
        leftRear.setPower(maxSpeed * (drivePowers[1] / maxPower));
        rightFront.setPower(maxSpeed * (drivePowers[2] / maxPower));
        rightRear.setPower(maxSpeed * (drivePowers[3] / maxPower));

        state = HardwareStates.ON;
    }

    /** Parameters for the power of each drive motor (updated 9/13/25)
     * @param forward The forward power of the given drive motor (backwards if negative)
     * @param strafe The strafe power of the given drive motor (left <-> right)
     * @param turn The rotation power of the given drive motor (in radians [probably lol])
     */
    public void driveField(double forward, double strafe, double turn) {
        /* drive data to polar coordinate values */
        double theta = Math.atan2(forward, strafe); //rotation
        double r = Math.hypot(strafe, forward); //distance
        /* polar to cartesian coordinates conversion */
        theta = AngleUnit.normalizeRadians(theta - imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS)); //makes the initial rotation more accurate
        double newForward = r * Math.sin(theta); //equivalent to the y coordinate
        double newStrafe = r * Math.cos(theta); //equivalent to the x coordinate

        this.drive(newForward, newStrafe, turn);
    }

    /** Parameter for setting the power of all drive motors at once (updated 10/16/25)
     * @param power The motor power desired to be given to all motors (can be used for a primitive brake)
     */
    @SuppressWarnings("ALL")
    public void setGlobalPowers(double power) {
        leftFront.setPower(power);
        leftRear.setPower(power);
        rightFront.setPower(power);
        rightRear.setPower(power);

        if (power == 0.0) {
            state = HardwareStates.OFF;
        } else {
            state = HardwareStates.ON;
        }
    }
}
