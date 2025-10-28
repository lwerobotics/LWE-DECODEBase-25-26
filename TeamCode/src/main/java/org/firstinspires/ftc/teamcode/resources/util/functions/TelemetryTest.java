package org.firstinspires.ftc.teamcode.resources.util.functions;

import com.bylazar.telemetry.PanelsTelemetry;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.resources.util.enums.TelemetryStates;

public class TelemetryTest {

    //very crude i know though if the function doesn't run at all with the bind then i know it failed
    public void telemetryTest(PanelsTelemetry panels, Telemetry ftc) {
        panels.getTelemetry().addData("Telemetry Test (Panels/FTC): ", TelemetryStates.SUCCESS.toString()); //tests to see if telemetry works on panels and DHub together
        panels.getTelemetry().update(ftc); //updates both telemetries
    }
}
