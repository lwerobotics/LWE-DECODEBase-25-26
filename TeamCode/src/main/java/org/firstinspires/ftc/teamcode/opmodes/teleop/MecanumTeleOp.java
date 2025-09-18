package org.firstinspires.ftc.teamcode.opmodes.teleop;

import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.resources.commands.drive.SetGlobalPowers;
import org.firstinspires.ftc.teamcode.resources.subsystems.drive.Drivetrain;
import org.firstinspires.ftc.teamcode.resources.util.FilterStickInput;

@TeleOp(group = "TeleOp", name = "MecanumTOp")
public class MecanumTeleOp extends CommandOpMode {
    /* hardware+utilities */
    private Drivetrain drivetrain;
    private FilterStickInput fsi; //NOTE: probably would be good to first test without the filter to see if it is even needed
    private GamepadEx driverOp;
    private GamepadEx toolOp;
    /* drive commands (prolly not needed but you never know) */
    private SetGlobalPowers setGlobalPowers;

    @Override
    public void initialize() {
        /* init */
        drivetrain = new Drivetrain();
        fsi = new FilterStickInput();
        driverOp = new GamepadEx(gamepad1);
        toolOp = new GamepadEx(gamepad2);
        /* drive */
        drivetrain.init(hardwareMap);
        drivetrain.driveField(fsi.filterStickInput(driverOp.getLeftX()), fsi.filterStickInput(driverOp.getLeftY()), fsi.filterStickInput(driverOp.getRightY()));
        //NOTE: not 100% bout this ^
        /* INSERT HEADER HERE */
        /* NOTE: in the future once the bot is actually built this area would use GamepadEx buttons/triggers attached to instant co*/
    }
}
