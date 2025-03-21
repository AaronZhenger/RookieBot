package kitbot.frc.robot.Commands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import kitbot.frc.robot.RobotContainer;
import kitbot.frc.robot.Subsystems.FlywheelsSubsystem.FlywheelsSubsystem;

public class FlywheelCommand extends Command {

    private XboxController joy;

    private FlywheelsSubsystem flywheels = RobotContainer.getOuttake();

    public FlywheelCommand(XboxController joy) {
        this.joy = joy;
        addRequirements(flywheels);
    }

    @Override
    public void execute() {
        double rpm = joy.getRightBumperButton() ? 0.7 : 0;
        rpm = joy.getBButton() ? 0.05 : 0;
        flywheels.setRpm(rpm);
    }
}
