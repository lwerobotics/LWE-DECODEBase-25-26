package org.firstinspires.ftc.teamcode.opmodes.teleop;

import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.seattlesolvers.solverslib.drivebase.MecanumDrive;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

//import these things as needed in the future
import org.firstinspires.ftc.teamcode.resources.commands.drive.SetGlobalPowers;
import org.firstinspires.ftc.teamcode.resources.subsystems.drive.Drivetrain;

@TeleOp(group = "TeleOP", name = "CompTOp")
@Disabled
public class SolversTestTele extends CommandOpMode {
    private final MecanumDrive drivetrain;
    private final GamepadEx gamepad1;

    @Override
    public void initialize() {

    }
}
