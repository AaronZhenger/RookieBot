package kitbot.frc.robot.Subsystems.DriveSubsystem;

import static edu.wpi.first.units.Units.MetersPerSecond;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.ControlType;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;

import edu.wpi.first.units.measure.LinearVelocity;
import kitbot.frc.robot.Constants.DriveConstants;

public class DriveIONeo implements DriveIO {

    private SparkMax left1 = new SparkMax(DriveConstants.kLeftFrontID.getDeviceNumber(), MotorType.kBrushless);
    private SparkMax left2 = new SparkMax(DriveConstants.kLeftBackID.getDeviceNumber(), MotorType.kBrushless);
    private SparkMax right1 = new SparkMax(DriveConstants.kRightFrontID.getDeviceNumber(), MotorType.kBrushless);
    private SparkMax right2 = new SparkMax(DriveConstants.kRightBackID.getDeviceNumber(), MotorType.kBrushless);
    private SparkMaxConfig driveConfig = new SparkMaxConfig();
    private SparkMaxConfig driveConfigInverted = new SparkMaxConfig();

    //private Pigeon2 gyro = new Pigeon2(DriveConstants.kPigeonID);

    public DriveIONeo() {
        driveConfig
            .idleMode(IdleMode.kCoast)
            .inverted(false)
            .smartCurrentLimit(60);
        driveConfig.encoder
            .positionConversionFactor(DriveConstants.kPositionConversionFactor)
            .velocityConversionFactor(DriveConstants.kPositionConversionFactor/60);
        driveConfig.closedLoop
            .pid(DriveConstants.kP, DriveConstants.kP, DriveConstants.kP)
            .maxMotion
                .maxVelocity(DriveConstants.kV)
                .maxAcceleration(DriveConstants.kA);
        driveConfigInverted
            .idleMode(IdleMode.kCoast)
            .inverted(true)
            .smartCurrentLimit(60);
        driveConfigInverted.encoder
            .positionConversionFactor(DriveConstants.kPositionConversionFactor)
            .velocityConversionFactor(DriveConstants.kPositionConversionFactor/60);
        driveConfigInverted.closedLoop
            .pid(DriveConstants.kP, DriveConstants.kP, DriveConstants.kP)
            .maxMotion
                .maxVelocity(DriveConstants.kV)
                .maxAcceleration(DriveConstants.kA);

        left1.configure(driveConfigInverted, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        right1.configure(driveConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        left2.configure(driveConfigInverted, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        right2.configure(driveConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    }

    @Override
    public void updateInputs(DriveIOInputsAutoLogged inputs) {
        inputs.leftSpeed =left1.getAbsoluteEncoder().getVelocity();
        inputs.rightSpeed = right1.getAbsoluteEncoder().getVelocity();
        inputs.leftDistanceTravelled = left1.getAbsoluteEncoder().getPosition();
        inputs.rightDistanceTravelled = right1.getAbsoluteEncoder().getPosition();
    }

    @Override
    public void setLeft(LinearVelocity rpm) {
        left1.getClosedLoopController().setReference(rpm.abs(MetersPerSecond), ControlType.kVelocity);
        left2.getClosedLoopController().setReference(rpm.abs(MetersPerSecond), ControlType.kVelocity);
    }

    @Override
    public void setRight(LinearVelocity rpm) {
        right1.getClosedLoopController().setReference(rpm.abs(MetersPerSecond), ControlType.kVelocity);
        right2.getClosedLoopController().setReference(rpm.abs(MetersPerSecond), ControlType.kVelocity);
    }
    
    public double getGyroRotation() {
        return 0;
        //return gyro.getYaw().getValueAsDouble();
    }
}
