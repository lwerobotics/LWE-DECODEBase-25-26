package org.firstinspires.ftc.teamcode.opmodes.teleop;

import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.seattlesolvers.solverslib.command.SequentialCommandGroup;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.resources.commands.drive.DriveField;
import org.firstinspires.ftc.teamcode.resources.commands.drive.InitDrive;
import org.firstinspires.ftc.teamcode.resources.commands.drive.SetGlobalPowers;
import org.firstinspires.ftc.teamcode.resources.subsystems.drive.Drivetrain;
import org.firstinspires.ftc.teamcode.resources.util.FilterStickInput;

@TeleOp(group = "TeleOp", name = "CompTeleOp")
public class MecanumTeleOp extends CommandOpMode {
    /* hardware+utilities */
    private Drivetrain drivetrain;
    private FilterStickInput fsi; //NOTE: probably would be good to first test without the filter to see if it is even needed
    private GamepadEx driverOp;
    private GamepadEx toolOp;
    /* drive commands (prolly not needed but you never know) */
    private SetGlobalPowers setGlobalPowers;
    private InitDrive initDrive;

    @Override
    public void initialize() {
        /* init */
        drivetrain = new Drivetrain();
        fsi = new FilterStickInput();
        driverOp = new GamepadEx(gamepad1);
        toolOp = new GamepadEx(gamepad2);
        /* drive */
        schedule(new SequentialCommandGroup(
                new InitDrive(hardwareMap, drivetrain),
                new DriveField(drivetrain, driverOp.getLeftX(), driverOp.getLeftY(), driverOp.getRightX())
        ));
        /* INSERT HEADER HERE */
        /* NOTE: in the future once the bot is actually built this area would use GamepadEx buttons/triggers attached to instant co*/
    }
}
