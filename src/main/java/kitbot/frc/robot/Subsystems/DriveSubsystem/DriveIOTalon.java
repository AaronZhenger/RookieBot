package kitbot.frc.robot.Subsystems.DriveSubsystem;

import static edu.wpi.first.units.Units.RPM;

import com.ctre.phoenix6.hardware.Pigeon2;

import edu.wpi.first.math.util.Units;
import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.units.measure.AngularVelocity;
import edu.wpi.first.units.measure.LinearVelocity;
import kitbot.frc.robot.Constants.DriveConstants;
import kitbot.lib.drivers.ComplexGearRatio;
import kitbot.lib.motors.real.MotorConfiguration;
import kitbot.lib.motors.real.SteelTalonFX;
import kitbot.lib.motors.real.MotorConfiguration.IdleState;
import kitbot.lib.motors.real.MotorConfiguration.MotorMode;

public class DriveIOTalon implements DriveIO {

    private SteelTalonFX left = new SteelTalonFX(DriveConstants.kLeftFrontID);
    private SteelTalonFX right = new SteelTalonFX(DriveConstants.kRightFrontID);

    private MotorConfiguration driveConfig = new MotorConfiguration();

    private Pigeon2 gyro = new Pigeon2(DriveConstants.kPigeonID);

    public DriveIOTalon() {
        driveConfig.idleState = IdleState.kBrake;
        driveConfig.gearRatio = new ComplexGearRatio(DriveConstants.kDriveGearRatio);
        driveConfig.mode = MotorMode.kFlywheel;
        driveConfig.currentLimit = 60;
        driveConfig.finalDiameterMeters = Units.inchesToMeters(DriveConstants.kWheelDiameter);
        driveConfig.maxVelocity = driveConfig.getStandardMaxVelocity(5676);
        driveConfig.maxAcceleration = driveConfig.getStandardMaxVelocity(5676)/3.0;
        driveConfig.kP = DriveConstants.kP;
        driveConfig.kI = DriveConstants.kI;
        driveConfig.kD = DriveConstants.kD;
        driveConfig.kV = DriveConstants.kMaxVelocity;
        driveConfig.kA = DriveConstants.kMaxAcceleration;

        left.apply(driveConfig);
        right.apply(driveConfig);
    }

    @Override
    public void updateInputs(DriveIOInputsAutoLogged inputs) {
        inputs.leftSpeed = left.getEncoderVelocity();
        inputs.rightSpeed = right.getEncoderVelocity();
        inputs.leftDistanceTravelled = left.getEncoderPosition()/1;
        inputs.rightDistanceTravelled = right.getEncoderPosition()/1;
    }


    
    public Angle getGyroRotation() {
        return gyro.getYaw().getValue();
    }

    @Override
    public void setLeft(LinearVelocity rpm) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setLeft'");
    }

    @Override
    public void setRight(LinearVelocity rpm) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setRight'");
    }
}
