package kitbot.frc.robot.Subsystems.FlywheelsSubsystem;

import org.littletonrobotics.junction.Logger;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class FlywheelsSubsystem extends SubsystemBase {
    private FlywheelsIO io;
    private FlywheelsIOInputsAutoLogged inputs;
    
    private double rpm = 0;

    public FlywheelsSubsystem() {
        io = new FlywheelsIONeo();
        inputs = new FlywheelsIOInputsAutoLogged();
    }
    
    @Override
    public void periodic() {
        Logger.recordOutput("Flywheels Inverse: ", inputs.flywheelSpeed<4);
        Logger.recordOutput("Flywheels Estimated RPM: ", String.format("%.1f", inputs.flywheelSpeed));
        io.setFlywheelSpeed(rpm);
        io.updateInputs(inputs);
    }

    public void setRpm(double rpm) {
        this.rpm = rpm;
    }

    public boolean isOccupied() {
        return io.occupied();
    }
}
