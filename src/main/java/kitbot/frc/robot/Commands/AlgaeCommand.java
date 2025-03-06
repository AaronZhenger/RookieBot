package kitbot.frc.robot.Commands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import kitbot.frc.robot.RobotContainer;
import kitbot.frc.robot.Subsystems.AlgaeSubsystem.AlgaeSubsystem;

public class AlgaeCommand extends Command{
    private AlgaeSubsystem algae = RobotContainer.getAlgae();
    private XboxController joy;

    public AlgaeCommand(XboxController joy) {
        this.joy = joy;
        addRequirements(algae);
    }

    @Override
    public void execute() {
        double angle = joy.getRightBumperButton() ? -1.4 : 0;
        algae.setAngle(angle);
        double negative = joy.getLeftBumperButton() ? -1 : 1;
        algae.setSpeed(negative*0.2*joy.getLeftTriggerAxis());
    }
}
