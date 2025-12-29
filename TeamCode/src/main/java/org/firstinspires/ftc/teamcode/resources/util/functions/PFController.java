package org.firstinspires.ftc.teamcode.resources.util.functions;

@SuppressWarnings({"FieldMayBeFinal", "FieldCanBeLocal"})
public class PFController {
    private double highVel, lowVel, targetVel, P, F;

    public PFController(double high, double low, double proportional, double feedforward) {
        this.highVel = high;
        this.lowVel = low;
        this.targetVel = this.highVel;
        this.P = proportional;
        this.F = feedforward;
    }

    //continue this later commit for now
}
