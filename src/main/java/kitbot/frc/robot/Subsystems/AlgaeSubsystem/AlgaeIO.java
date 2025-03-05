package kitbot.frc.robot.Subsystems.AlgaeSubsystem;

import static edu.wpi.first.units.Units.Rotations;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.ControlType;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;

import edu.wpi.first.units.measure.Angle;

import com.revrobotics.spark.config.SparkMaxConfig;

import kitbot.frc.robot.Constants.AlgaeConstants;

public class AlgaeIO {
    private SparkMax pivot = new SparkMax(AlgaeConstants.kPivotID, MotorType.kBrushless);
    private SparkMax flywheel = new SparkMax(AlgaeConstants.kFlywheelID, MotorType.kBrushless);
    private SparkMaxConfig pivotConfig = new SparkMaxConfig();
    
    public AlgaeIO() {
        pivotConfig
            .idleMode(IdleMode.kBrake)
            .inverted(false)
            .smartCurrentLimit(40);
        pivotConfig.encoder
            .positionConversionFactor(AlgaeConstants.kPivotPositionConversionFactor)
            .velocityConversionFactor(AlgaeConstants.kPivotPositionConversionFactor/60);
        pivotConfig.closedLoop
            .pid(AlgaeConstants.kPivotP, AlgaeConstants.kPivotI, AlgaeConstants.kPivotD)
            .maxMotion
                .maxVelocity(5676*AlgaeConstants.kPivotPositionConversionFactor)
                .maxAcceleration(5676*AlgaeConstants.kPivotPositionConversionFactor/2);
    }

    public void setPivotAngle(Angle angle) {
        pivot.getClosedLoopController().setReference(angle.in(Rotations), ControlType.kPosition);
    }

    public void setFlywheelVelocity(double speed) {
        flywheel.set(speed);
    }
}
