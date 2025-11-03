package org.firstinspires.ftc.teamcode.resources.commands.utility;

import com.seattlesolvers.solverslib.command.InstantCommand;

import org.firstinspires.ftc.teamcode.resources.hardware.Drivetrain;
import org.firstinspires.ftc.teamcode.resources.hardware.Gate;
import org.firstinspires.ftc.teamcode.resources.hardware.Intake;
import org.firstinspires.ftc.teamcode.resources.hardware.Outtake;

public class KillRobot extends InstantCommand {
    private final Drivetrain drive;
    private final Intake intake;
    private final Outtake outtake;
    private final Gate gate;

    public KillRobot(Drivetrain drivetrain, Intake intake, Outtake outtake, Gate gate) {
        this.drive = drivetrain;
        this.intake = intake;
        this.outtake = outtake;
        this.gate = gate;
    }

    @Override
    public void execute() {
        drive.setGlobalPowers(0.0);
        intake.stop();
        outtake.off();
        gate.block();
    }
}
