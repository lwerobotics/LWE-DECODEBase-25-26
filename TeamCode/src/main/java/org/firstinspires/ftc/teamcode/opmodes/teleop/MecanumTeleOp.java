package org.firstinspires.ftc.teamcode.opmodes.teleop;

import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.resources.commands.drive.DriveField;
import org.firstinspires.ftc.teamcode.resources.commands.drive.SetGlobalPowers;
import org.firstinspires.ftc.teamcode.resources.subsystems.drive.Drivetrain;
import org.firstinspires.ftc.teamcode.resources.util.FilterStickInput;

@TeleOp(group = "TeleOp", name = "MecanumTOp")
public class MecanumTeleOp extends CommandOpMode {
    /* hardware+utilities */
    private Drivetrain drivetrain; //ONLY USE THIS FOR "init()", NOTHING ELSE !!!
    private GamepadEx driverOp;
    private GamepadEx toolOp;
    /* drive params */
    private double forward;
    private double strafe;
    private double turn;
    /* drive commands */
    private DriveField driveField;
    private SetGlobalPowers setGlobalPowers;

    @Override
    public void initialize() {
        /* init */
        drivetrain = new Drivetrain();
        driverOp = new GamepadEx(gamepad1);
        toolOp = new GamepadEx(gamepad2);

        drivetrain.init(hardwareMap);
        schedule(driveField);
    }
}
