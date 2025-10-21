package org.firstinspires.ftc.teamcode.opmodes.teleop;

import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.seattlesolvers.solverslib.command.SequentialCommandGroup;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.resources.commands.drive.DriveField;
import org.firstinspires.ftc.teamcode.resources.commands.initializers.InitDrive;
import org.firstinspires.ftc.teamcode.resources.commands.drive.SetGlobalPowers;
import org.firstinspires.ftc.teamcode.resources.subsystems.drive.Drivetrain;
import org.firstinspires.ftc.teamcode.resources.util.FilterStickInput;

@TeleOp(group = "TeleOp OpModes", name = "Competition TeleOp")
public class MecanumTeleOp extends CommandOpMode {
    /* hardware+utilities */
    private Drivetrain drivetrain;
    private FilterStickInput fsi;
    private GamepadEx driverOp;
    private GamepadEx toolOp;
    /* drive commands*/
    private SetGlobalPowers setGlobalPowers;

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
                new DriveField(drivetrain, fsi.filterStickInput(driverOp.getLeftX()), fsi.filterStickInput(driverOp.getLeftY()), fsi.filterStickInput(driverOp.getRightX()))
        ));
        /* INSERT HEADER HERE */
        /* NOTE: in the future once the bot is actually built this area would use GamepadEx buttons/triggers attached to instant co*/
    }
}
