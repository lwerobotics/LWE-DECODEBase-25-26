package org.firstinspires.ftc.teamcode.resources.commands.initializers;

import androidx.annotation.NonNull;

import com.bylazar.telemetry.TelemetryManager;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.CommandBase;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.resources.hardware.Intake;

public class InitIntake extends CommandBase {
    private final Intake intake;
    private final HardwareMap hardware;
    private final TelemetryManager pTele;
    private final Telemetry fTele;

    public InitIntake(@NonNull TelemetryManager panels, @NonNull Telemetry ftc, @NonNull HardwareMap hMap, Intake subsystem) {
        this.hardware = hMap;
        this.intake = subsystem;
        this.pTele = panels;
        this.fTele = ftc;
        addRequirements(subsystem);
    }

    @Override
    public void initialize() {
        intake.initMotor(pTele, fTele, hardware);
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
