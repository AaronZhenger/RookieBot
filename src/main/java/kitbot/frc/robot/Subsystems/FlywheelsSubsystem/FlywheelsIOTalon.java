// package kitbot.frc.robot.Subsystems.FlywheelsSubsystem;

// import edu.wpi.first.math.util.Units;
// import edu.wpi.first.wpilibj.DigitalInput;
// import kitbot.frc.robot.Constants.FlywheelConstants;
// import kitbot.lib.drivers.ComplexGearRatio;
// import kitbot.lib.motors.real.MotorConfiguration;
// import kitbot.lib.motors.real.SteelTalonFX;
// import kitbot.lib.motors.real.MotorConfiguration.IdleState;
// import kitbot.lib.motors.real.MotorConfiguration.MotorMode;

// public class FlywheelsIOTalon implements FlywheelsIO {

//     private SteelTalonFX flywheel = new SteelTalonFX(FlywheelConstants.kFlywheelID);
//     private MotorConfiguration flywheelConfig = new MotorConfiguration();

//     private DigitalInput beamBreaker = new DigitalInput(FlywheelConstants.DigitalInputID);

//     public FlywheelsIOTalon() {
//         flywheelConfig.currentLimit = 40;
//         flywheelConfig.finalDiameterMeters = Units.inchesToMeters(FlywheelConstants.kWheelDiameter);
//         flywheelConfig.gearRatio = new ComplexGearRatio(FlywheelConstants.kGearRatio);
//         flywheelConfig.idleState = IdleState.kCoast;
//         flywheelConfig.mode = MotorMode.kFlywheel;
//         flywheelConfig.kP = FlywheelConstants.kP;
//         flywheelConfig.kI = FlywheelConstants.kI;
//         flywheelConfig.kD = FlywheelConstants.kD;
//         flywheelConfig.kV = FlywheelConstants.kV;
//         flywheelConfig.kA = FlywheelConstants.kA;

//         flywheelConfig.maxVelocity = flywheelConfig.getStandardMaxVelocity(5676);
//         flywheelConfig.maxAcceleration = flywheelConfig.maxVelocity/2;

//         flywheel.apply(flywheelConfig);
//     }
    
//     @Override
//     public void updateInputs(FlywheelsIOInputsAutoLogged inputs) {
//         inputs.flywheelSpeed = flywheel.getEncoderVelocity();
//     }

//     @Override
//     public void setFlywheelSpeed(double rpm) {
//         flywheel.setSetpoint(rpm);
//     }

//     public boolean occupied() {
//         return beamBreaker.get();
//     }
    
// }
