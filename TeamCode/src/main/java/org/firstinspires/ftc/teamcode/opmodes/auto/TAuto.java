package org.firstinspires.ftc.teamcode.opmodes.auto;

import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

//import these things as needed in the future
import org.firstinspires.ftc.teamcode.resources.commands.drive.SetGlobalPowers;
import org.firstinspires.ftc.teamcode.resources.subsystems.*;

@Autonomous(group = "Autonomous", name = "TempAuto")
@Disabled
public class TAuto extends CommandOpMode {
    //vars here (template command var will be here for now)
    private SetGlobalPowers tempCommand;
    private Drivetrain tempSubsystem;

    @Override
    public void initialize() {
        //stuff here (template command will be init'd here for now)
        tempCommand = new SetGlobalPowers(tempSubsystem = new Drivetrain());
        schedule(tempCommand); //add whatever commands here
    }
}
