package kitbot.frc.robot.Commands;

import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import kitbot.frc.robot.RobotContainer;
import kitbot.frc.robot.Constants.DriveConstants;
import kitbot.frc.robot.Subsystems.DriveSubsystem.DriveSubsystem;

public class DriveCommand extends Command {

    private XboxController joy;

    private DriveSubsystem drive = RobotContainer.getDriveSubsystem();

    public DriveCommand(XboxController joy) {
        this.joy = joy;
        addRequirements(drive);
    }

    @Override
    public void execute() {
        double yValue = Math.abs(joy.getLeftY())<0.15?0.0:joy.getLeftY();
        double rotation = Math.abs(joy.getRightX())<0.15?0.0:joy.getRightX();


        drive.setCurrentSpeeds(new ChassisSpeeds(
            yValue * DriveConstants.kMaxMPS,
            0,
            rotation * Math.PI * 2.5
        ));
    }
}
