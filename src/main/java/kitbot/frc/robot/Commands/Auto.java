package kitbot.frc.robot.Commands;

import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import kitbot.frc.robot.RobotContainer;
import kitbot.frc.robot.Constants.DriveConstants;
import kitbot.frc.robot.Subsystems.DriveSubsystem.DriveSubsystem;
import kitbot.frc.robot.Subsystems.FlywheelsSubsystem.FlywheelsSubsystem;

public class Auto extends Command {

    private DriveSubsystem drive = RobotContainer.getDriveSubsystem();
    private FlywheelsSubsystem flywheels = RobotContainer.getOuttake();

    private Timer timer = new Timer();

    public Auto() {
        timer.reset();
    }

    @Override
    public void execute() {
        while (timer.get()<5.0)
            drive.setCurrentSpeeds(new ChassisSpeeds(DriveConstants.kMaxVelocity*0.5,
            0,
            0));
        while (timer.get()<7.0)
            flywheels.setRpm(0.7);
    }

    @Override
    public boolean isFinished() {
        return timer.get()>15;
    }
}
