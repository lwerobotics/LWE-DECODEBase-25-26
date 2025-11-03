package org.firstinspires.ftc.teamcode.resources.commands.initializers;

import androidx.annotation.NonNull;

import com.bylazar.telemetry.TelemetryManager;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.CommandBase;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.resources.hardware.Drivetrain;
public class InitDrive extends CommandBase {
    private final Drivetrain drive;
    private final HardwareMap hardware;
    private final TelemetryManager pTele;
    private final Telemetry fTele;

    /** Parameters for the initialization function of the drivetrain (updated 10/16/25)
     * @param hMap The hardware map used to register hardware to the robot (like motors, servos , actuators, etc.)
     * @param subsystem The subsystem used by the command (drivetrain, intake, outtake, etc.)
     */
    public InitDrive(@NonNull TelemetryManager panels, @NonNull Telemetry ftc, @NonNull HardwareMap hMap, Drivetrain subsystem) {
        this.drive = subsystem;
        this.hardware = hMap;
        this.pTele = panels;
        this.fTele = ftc;
        addRequirements(subsystem);
    }

    @Override
    public void initialize() {
        drive.initDrivetrain(hardware, pTele, fTele);
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}