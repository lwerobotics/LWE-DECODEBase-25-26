package org.firstinspires.ftc.teamcode.opmodes.testops;

import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

//import these things as needed in the future
import org.firstinspires.ftc.teamcode.resources.commands.*;
import org.firstinspires.ftc.teamcode.resources.subsystems.*;

@TeleOp(group = "TestOps", name = "TestTeleOp")
@Disabled
public class TempTestOp extends CommandOpMode {
    //vars here (template command var will be here for now)
    private TCommand tempCommand;
    private TSub tempSubsystem;

    @Override
    public void initialize() {
        //stuff here (template command will be init'd here for now)
        tempCommand = new TCommand(tempSubsystem = new TSub());
        schedule(tempCommand); //add whatever commands here
    }
}
