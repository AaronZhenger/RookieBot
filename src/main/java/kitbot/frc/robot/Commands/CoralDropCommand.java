package kitbot.frc.robot.Commands;

import edu.wpi.first.wpilibj2.command.Command;
import kitbot.frc.robot.RobotContainer;
import kitbot.frc.robot.Constants.FlywheelConstants;
import kitbot.frc.robot.Subsystems.FlywheelsSubsystem.FlywheelsSubsystem;

public class CoralDropCommand extends Command {

    private FlywheelsSubsystem flywheels = RobotContainer.getOuttake();

    public CoralDropCommand() {
        
    }

    @Override
    public void execute() {
        flywheels.setRpm(FlywheelConstants.kTargetRPM);
    }

    @Override
    public boolean isFinished() {
        return !flywheels.isOccupied();
    }
}
