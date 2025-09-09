package org.firstinspires.ftc.teamcode.opmodes.auto;

import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

//import these things as needed in the future
import org.firstinspires.ftc.teamcode.resources.commands.*;
import org.firstinspires.ftc.teamcode.resources.subsystems.*;

@Autonomous(group = "Autonomous", name = "TempAuto")
@Disabled
public class TAuto extends CommandOpMode {
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
