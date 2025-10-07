package org.firstinspires.ftc.teamcode.opmodes.testops;

import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.resources.subsystems.drive.Drivetrain;

@TeleOp(group = "TestOps", name = "TestTeleOp")
public class TestOp extends CommandOpMode {
    /* subsystems */
    private Drivetrain drive;

    @Override
    public void initialize() {
        /* init */
        drive = new Drivetrain();
    }
}
