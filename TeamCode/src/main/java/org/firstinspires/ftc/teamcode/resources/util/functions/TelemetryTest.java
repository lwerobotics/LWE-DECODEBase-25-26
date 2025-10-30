package org.firstinspires.ftc.teamcode.resources.util.functions;

import androidx.annotation.NonNull;

import com.bylazar.telemetry.TelemetryManager;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.resources.util.enums.HardwareStates;

public class TelemetryTest {

    /** Parameters for a temporary(?) tester function for testing sending telemetry between Panels and the Driver Hub
     * @param panels The telemetry that sends to Panels
     * @param ftc The telemetry that sends to the Driver Hub
     */
    public void telemetryTest(@NonNull TelemetryManager panels, @NonNull Telemetry ftc) { //@NonNull here is used as a precaution
        panels.addData("Telemetry Test (Panels): ", HardwareStates.SUCCESS.toString()); //tests to see if telemetry works on panels and DHub together
        ftc.addData("Telemetry Test (FTC): ", HardwareStates.SUCCESS);
        panels.update();
        ftc.update();
    }
}