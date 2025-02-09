package kitbot.frc.robot.Subsystems.FlywheelsSubsystem;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;

import edu.wpi.first.wpilibj.DigitalInput;
import kitbot.frc.robot.Constants.FlywheelConstants;

public class FlywheelsIONeo implements FlywheelsIO {

    private SparkMax flywheel = new SparkMax(7, MotorType.kBrushed);
    private SparkMaxConfig flywheelConfig = new SparkMaxConfig();

    private DigitalInput beamBreaker = new DigitalInput(FlywheelConstants.DigitalInputID);

    public FlywheelsIONeo() {

        flywheelConfig
            .smartCurrentLimit(40)
            .idleMode(IdleMode.kCoast);
        flywheelConfig.closedLoop
            .p(FlywheelConstants.kP)
            .i(FlywheelConstants.kI)
            .d(FlywheelConstants.kD);

        flywheel.configure(flywheelConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    }
    
    @Override
    public void updateInputs(FlywheelsIOInputsAutoLogged inputs) {
        inputs.flywheelSpeed = flywheel.getEncoder().getVelocity();
    }

    @Override
    public void setFlywheelSpeed(double rpm) {
        flywheel.set(rpm);
    }

    public boolean occupied() {
        return beamBreaker.get();
    }
    
}
