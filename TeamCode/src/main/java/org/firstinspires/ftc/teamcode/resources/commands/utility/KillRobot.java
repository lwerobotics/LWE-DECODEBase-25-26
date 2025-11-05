package org.firstinspires.ftc.teamcode.resources.commands.utility;

import com.seattlesolvers.solverslib.command.InstantCommand;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.resources.hardware.Drivetrain;
import org.firstinspires.ftc.teamcode.resources.hardware.Gate;
import org.firstinspires.ftc.teamcode.resources.hardware.Intake;
import org.firstinspires.ftc.teamcode.resources.hardware.Outtake;

public class KillRobot extends InstantCommand {
    private final Drivetrain drive;
    private final Intake intake;
    private final Outtake outtake;
    private final Gate gate;
    private final Telemetry telemetry;

    public KillRobot(Drivetrain drivetrain, Intake intake, Outtake outtake, Gate gate, Telemetry ftc) {
        this.drive = drivetrain;
        this.intake = intake;
        this.outtake = outtake;
        this.gate = gate;
        this.telemetry = ftc;
    }

    @Override
    public void execute() {
        telemetry.addData("Did it run? ", "yur");
        telemetry.update();
        drive.setGlobalPowers(0.0);
        intake.stop();
        outtake.off();
        gate.block();
    }
}
