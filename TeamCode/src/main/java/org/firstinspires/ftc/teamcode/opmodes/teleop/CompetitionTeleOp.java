package org.firstinspires.ftc.teamcode.opmodes.teleop;

import com.bylazar.telemetry.PanelsTelemetry;
import com.bylazar.telemetry.TelemetryManager;
import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.seattlesolvers.solverslib.command.SequentialCommandGroup;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.resources.commands.drive.DriveField;
import org.firstinspires.ftc.teamcode.resources.commands.initializers.InitDrive;
import org.firstinspires.ftc.teamcode.resources.commands.drive.SetGlobalDrivePowers;
import org.firstinspires.ftc.teamcode.resources.hardware.Drivetrain;
import org.firstinspires.ftc.teamcode.resources.util.functions.FilterStickInput;

@TeleOp(name = "CompOp")
public class CompetitionTeleOp extends CommandOpMode { //lock in bro
    /* hardware+utilities */
    private Drivetrain drivetrain;
    private FilterStickInput fsi;
    private GamepadEx driverOp;
    private GamepadEx toolOp;
    /* miscellaneous */
    private TelemetryManager panelsTelemetry;

    @Override
    public void initialize() {
        /* init */
        drivetrain = new Drivetrain();
        fsi = new FilterStickInput();
        driverOp = new GamepadEx(gamepad1);
        toolOp = new GamepadEx(gamepad2);
        panelsTelemetry = PanelsTelemetry.INSTANCE.getTelemetry();
        /* drive */
        schedule(new SequentialCommandGroup(
                new InitDrive(panelsTelemetry, telemetry, hardwareMap, drivetrain),
                new DriveField(drivetrain, fsi.filterStickInput(driverOp.getLeftX()), fsi.filterStickInput(driverOp.getLeftY()), fsi.filterStickInput(driverOp.getRightX()))
        ));
        /* INSERT HEADER HERE */
        /* NOTE: in the future once the bot is actually built this area would use GamepadEx buttons/triggers attached to instant co*/
        /* 10/29 NOTE: just copy from practice opmode once it actually works */
    }
}
