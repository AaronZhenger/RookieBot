package kitbot.frc.robot.Subsystems.DriveSubsystem;

import static edu.wpi.first.units.Units.MetersPerSecond;

import com.revrobotics.spark.SparkClosedLoopController;
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
    private SparkClosedLoopController left1Controller = left1.getClosedLoopController();
    private SparkClosedLoopController left2Controller = left2.getClosedLoopController();
    private SparkClosedLoopController right1Controller = right1.getClosedLoopController();
    private SparkClosedLoopController right2Controller = right2.getClosedLoopController();

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
    public void setLeft(LinearVelocity mps) {
        left1Controller.setReference(mps.in(MetersPerSecond), ControlType.kVelocity);
        left2Controller.setReference(mps.in(MetersPerSecond), ControlType.kVelocity);
    }

    @Override
    public void setRight(LinearVelocity mps) {
        right1Controller.setReference(mps.in(MetersPerSecond), ControlType.kVelocity);
        right2Controller.setReference(mps.in(MetersPerSecond), ControlType.kVelocity);
    }
    
    public double getGyroRotation() {
        return 0;
        //return gyro.getYaw().getValueAsDouble();
    }
}
