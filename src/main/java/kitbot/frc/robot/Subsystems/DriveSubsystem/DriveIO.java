package kitbot.frc.robot.Subsystems.DriveSubsystem;

import org.littletonrobotics.junction.AutoLog;

import edu.wpi.first.units.measure.LinearVelocity;

public interface DriveIO {
    @AutoLog
    public static class DriveIOInputs {
        public double leftSpeed;
        public double rightSpeed;
        public double leftDistanceTravelled;
        public double rightDistanceTravelled;
    }

    public void updateInputs(DriveIOInputsAutoLogged inputs);

    public void setLeft(LinearVelocity rpm);
    
    public void setRight(LinearVelocity rpm);   

    public double getGyroRotation();
}
