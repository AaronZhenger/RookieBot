package kitbot.frc.robot;

import edu.wpi.first.math.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.math.util.Units;
import kitbot.lib.drivers.CANDeviceId;

public class Constants {

    public static Mode currentMode = Mode.SIM;
    public static enum Mode {
        /** Running on a real robot. */
        REAL,
    
        /** Running a physics simulator. */
        SIM,
    
        /** Replaying from a log file. */
        REPLAY
      }

    public class DriveConstants {
        public static final CANDeviceId kLeftFrontID = new CANDeviceId(3);
        public static final CANDeviceId kRightFrontID = new CANDeviceId(6);
        public static final CANDeviceId kLeftBackID = new CANDeviceId(4);
        public static final CANDeviceId kRightBackID = new CANDeviceId(5);
        public static final int kPigeonID = 0;

        public static final double kDriveGearRatio = 1.0/8.45;
        public static final double kWheelDiameter = 0.1524; //0.1524

        public static final double kP = 0.35;
        public static final double kI = 0.0;
        public static final double kD = 0.0;
        public static final double kV = 1.52;
        public static final double kA = 0.61;

        public static final double kPositionConversionFactor = Math.PI*kWheelDiameter*kDriveGearRatio;
        public static final double kMaxVelocity = 5676*kPositionConversionFactor/60;
        public static final double kMaxAcceleration = kMaxVelocity/1.8;

        public static final DifferentialDriveKinematics m_kinematics = 
            new DifferentialDriveKinematics(Units.inchesToMeters(21.5));
        
    }
    public class OperatorConstants {
        public static final int kJoyPort = 0;
    }
    public class FlywheelConstants {
        public static final int DigitalInputID = 0;

        public static final double kP = 0;
        public static final double kI = 0;
        public static final double kD = 0;
        public static final double kV = 0.77;
        public static final double kA = 0.21;

        public static final double kGearRatio = 0.5;
        public static final double kWheelDiameter = Units.inchesToMeters(4);

        public static final double kTargetRPM = 3*60;
    }
    public class AlgaeConstants {
        public static final int kPivotID = 9;
        public static final int kFlywheelID = 10;
        
        public static final double kPivotGearRatio = 1.0/72.0;

        public static final double kPivotPositionConversionFactor = Math.PI*kPivotGearRatio*2.0;

        public static final double kPivotP = 0.4;
        public static final double kPivotI = 0.0;
        public static final double kPivotD = 0.0;
    }
    public class AutoConstants {

    }
}
