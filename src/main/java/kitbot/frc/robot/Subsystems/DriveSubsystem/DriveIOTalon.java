package kitbot.frc.robot.Subsystems.DriveSubsystem;

import static edu.wpi.first.units.Units.Degrees;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.Pigeon2;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.math.util.Units;
import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.units.measure.LinearVelocity;
import kitbot.frc.robot.Constants.DriveConstants;
import kitbot.lib.drivers.ComplexGearRatio;
import kitbot.lib.motors.real.MotorConfiguration;
import kitbot.lib.motors.real.SteelTalonFX;
import kitbot.lib.motors.real.MotorConfiguration.IdleState;
import kitbot.lib.motors.real.MotorConfiguration.MotorMode;

//TODO: Rework File (with CTRE motors not teamlib)
public class DriveIOTalon implements DriveIO {

    private TalonFX left1 = new TalonFX(DriveConstants.kLeftFrontID.getDeviceNumber());
    private TalonFX left2 = new TalonFX(DriveConstants.kLeftBackID.getDeviceNumber());
    private TalonFX right1 = new TalonFX(DriveConstants.kRightFrontID.getDeviceNumber());
    private TalonFX right2 = new TalonFX(DriveConstants.kRightBackID.getDeviceNumber());

    private TalonFXConfiguration leftConfig = new TalonFXConfiguration();
    private TalonFXConfiguration rightConfig = new TalonFXConfiguration(); 

    // private Pigeon2 gyro = new Pigeon2(DriveConstants.kPigeonID);

    public DriveIOTalon() {
        // driveConfig.idleState = IdleState.kBrake;
        // driveConfig.gearRatio = new ComplexGearRatio(DriveConstants.kDriveGearRatio);
        // driveConfig.mode = MotorMode.kFlywheel;
        // driveConfig.currentLimit = 60;
        // driveConfig.finalDiameterMeters = Units.inchesToMeters(DriveConstants.kWheelDiameter);
        // driveConfig.maxVelocity = driveConfig.getStandardMaxVelocity(5676);
        // driveConfig.maxAcceleration = driveConfig.getStandardMaxVelocity(5676)/3.0;
        // driveConfig.kP = DriveConstants.kP;
        // driveConfig.kI = DriveConstants.kI;
        // driveConfig.kD = DriveConstants.kD;
        // driveConfig.kV = DriveConstants.kMaxVelocity;
        // driveConfig.kA = DriveConstants.kMaxAcceleration;

        // left.apply(driveConfig);
        // right.apply(driveConfig);
    }

    @Override
    public void updateInputs(DriveIOInputsAutoLogged inputs) {
        // inputs.leftSpeed = left.getEncoderVelocity();
        // inputs.rightSpeed = right.getEncoderVelocity();
        // inputs.leftDistanceTravelled = left.getEncoderPosition()/1;
        // inputs.rightDistanceTravelled = right.getEncoderPosition()/1;
    }


    
    public Angle getGyroRotation() {
        // return gyro.getYaw().getValue();
        return Angle.ofBaseUnits(0, Degrees);
    }

    @Override
    public void setLeft(LinearVelocity rpm) {
    }

    @Override
    public void setRight(LinearVelocity rpm) {
    }
}
