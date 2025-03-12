package kitbot.frc.robot.Commands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import kitbot.frc.robot.RobotContainer;
import kitbot.frc.robot.Subsystems.AlgaeSubsystem.AlgaeSubsystem;

public class AlgaeCommand extends Command{
    private AlgaeSubsystem algae = RobotContainer.getAlgae();
    private XboxController joy;
    private double angle = 0.0;

    public AlgaeCommand(XboxController joy) {
        this.joy = joy;
        addRequirements(algae);
    }

    @Override
    public void execute() {
        if (angle==0.0)
            angle = 0.2;
        if (joy.getAButtonPressed()) {
            if (angle==0.2)
                angle = -1;
            else angle = 0.2;
        }
        algae.setAngle(angle);
        algae.setSpeed(0.3*(joy.getLeftTriggerAxis()-joy.getRightTriggerAxis()));
    }
}
