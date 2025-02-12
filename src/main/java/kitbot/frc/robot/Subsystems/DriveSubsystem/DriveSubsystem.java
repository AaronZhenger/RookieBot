package kitbot.frc.robot.Subsystems.DriveSubsystem;

import static edu.wpi.first.units.Units.MetersPerSecond;

import org.littletonrobotics.junction.Logger;

import edu.wpi.first.math.estimator.DifferentialDrivePoseEstimator;
import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.units.measure.LinearVelocity;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import kitbot.frc.robot.Constants.DriveConstants;

public class DriveSubsystem extends SubsystemBase {
    private DriveIO io;
    private DriveIOInputsAutoLogged inputs;

    private ChassisSpeeds currentSpeeds;

    private SlewRateLimiter leftFilter;
    private SlewRateLimiter rightFilter;

    private DifferentialDrivePoseEstimator pose;

    public DriveSubsystem() {
        leftFilter = new SlewRateLimiter(4.5);
        rightFilter = new SlewRateLimiter(4.5);
        io = new DriveIONeo();
        inputs = new DriveIOInputsAutoLogged();
        pose = new DifferentialDrivePoseEstimator(DriveConstants.m_kinematics, Rotation2d.fromDegrees(io.getGyroRotation()), 
            inputs.leftDistanceTravelled, inputs.rightDistanceTravelled, new Pose2d(0, 0, new Rotation2d()));
        currentSpeeds = new ChassisSpeeds();
    }

    @Override
    public void periodic() {
        io.updateInputs(inputs);
        DifferentialDriveWheelSpeeds wheelSpeeds = DriveConstants.m_kinematics.toWheelSpeeds(currentSpeeds);

        io.setLeft(LinearVelocity.ofBaseUnits(leftFilter.calculate(wheelSpeeds.leftMetersPerSecond), MetersPerSecond));
        io.setRight(LinearVelocity.ofBaseUnits(rightFilter.calculate(wheelSpeeds.rightMetersPerSecond), MetersPerSecond));

        Logger.processInputs("Inputs: Drivetrain", inputs);
        Logger.recordOutput("Left Output", wheelSpeeds.leftMetersPerSecond);
        Logger.recordOutput("Right Output", wheelSpeeds.rightMetersPerSecond);
        SmartDashboard.putBoolean("isSeeb", true);
    }
    
    public void setCurrentSpeeds(ChassisSpeeds currentSpeeds) {
        this.currentSpeeds = currentSpeeds;
    }

    public ChassisSpeeds getCurrentSpeeds() {
        return currentSpeeds;
    }

    public Pose2d getPose() {
        return pose.getEstimatedPosition();//
    }

    public void resetPose(Pose2d pose) {
        this.pose.resetPose(pose);
    }
}
