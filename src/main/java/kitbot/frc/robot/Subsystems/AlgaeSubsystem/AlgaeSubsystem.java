package kitbot.frc.robot.Subsystems.AlgaeSubsystem;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class AlgaeSubsystem extends SubsystemBase{
    private AlgaeIO algaeIO;

    private double angle = 0;
    private double speed = 0;

    public AlgaeSubsystem() {
        algaeIO = new AlgaeIO();
    }

    @Override
    public void periodic() {
        algaeIO.setPivotAngle(angle);
        algaeIO.setFlywheelVelocity(speed);
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }
}
