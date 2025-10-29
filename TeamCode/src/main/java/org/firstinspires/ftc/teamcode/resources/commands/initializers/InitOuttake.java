package org.firstinspires.ftc.teamcode.resources.commands.initializers;

import androidx.annotation.NonNull;

import com.bylazar.telemetry.TelemetryManager;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.CommandBase;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.resources.hardware.Gate;
import org.firstinspires.ftc.teamcode.resources.hardware.Outtake;

public class InitOuttake extends CommandBase {
    private Outtake outtake;
    private Gate gate;
    private HardwareMap hardware;
    private TelemetryManager pTele;
    private Telemetry fTele;

    /** Parameters for the initialization function of the drivetrain (updated 10/16/25)
     * @param hMap The hardware map used to register hardware to the robot (like motors, servos , actuators, etc.)
     * @param subsystem The subsystem used by the command (drivetrain, intake, outtake, etc.)
     */
    public InitOuttake(@NonNull TelemetryManager panels, @NonNull Telemetry ftc, @NonNull HardwareMap hMap, Outtake subsystem, Gate subsystem2) {
        this.hardware = hMap;
        this.pTele = panels;
        this.fTele = ftc;
        this.outtake = subsystem;
        this.gate = subsystem2;
        addRequirements(subsystem);
    }

    @Override
    public void execute() {
        outtake.initOuttake(pTele, fTele, hardware);
        gate.initGate(pTele, fTele, hardware);
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
