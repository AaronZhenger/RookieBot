package kitbot.frc.robot.Subsystems.FlywheelsSubsystem;

import org.littletonrobotics.junction.AutoLog;

public interface FlywheelsIO {
    @AutoLog
    public static class FlywheelsIOInputs {
        public double flywheelSpeed;
    }

    public void updateInputs(FlywheelsIOInputsAutoLogged inputs);

    public void setFlywheelSpeed(double rpm);

    public boolean occupied();
}
