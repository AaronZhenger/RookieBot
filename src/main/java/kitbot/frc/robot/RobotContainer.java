// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package kitbot.frc.robot;

import java.io.IOException;

import org.json.simple.parser.ParseException;

import com.pathplanner.lib.auto.AutoBuilder;
import com.pathplanner.lib.config.ModuleConfig;
import com.pathplanner.lib.config.RobotConfig;
import com.pathplanner.lib.controllers.PPLTVController;

import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import kitbot.frc.robot.Commands.AlgaeCommand;
import kitbot.frc.robot.Commands.Auto;
import kitbot.frc.robot.Commands.DriveCommand;
import kitbot.frc.robot.Commands.FlywheelCommand;
import kitbot.frc.robot.Constants.OperatorConstants;
import kitbot.frc.robot.Subsystems.AlgaeSubsystem.AlgaeSubsystem;
import kitbot.frc.robot.Subsystems.DriveSubsystem.DriveSubsystem;
import kitbot.frc.robot.Subsystems.FlywheelsSubsystem.FlywheelsSubsystem;

public class RobotContainer {
  private XboxController joy = new XboxController(OperatorConstants.kJoyPort);
  private static DriveSubsystem driveSubsystem = new DriveSubsystem();
  private static FlywheelsSubsystem outtake = new FlywheelsSubsystem();
  private static AlgaeSubsystem algae = new AlgaeSubsystem();
    
      public RobotContainer() {
        RobotConfig config = new RobotConfig(36.287, 3, 
          new ModuleConfig(0.1524, 3.0, 1.1, DCMotor.getNEO(4), 60.0, 4), 21.5);
        try {
          config = RobotConfig.fromGUISettings();
        } catch (IOException e) {
          e.printStackTrace();
        } catch (ParseException e) {
          e.printStackTrace();
    
        }
    
        AutoBuilder.configure(
          driveSubsystem::getPose,
          driveSubsystem::resetPose, 
          driveSubsystem::getCurrentSpeeds, 
          (speeds, feedforwards) -> driveSubsystem.setCurrentSpeeds(speeds), 
          new PPLTVController(0.02), 
          config, 
          () -> {
            var alliance = DriverStation.getAlliance();
            if (alliance.isPresent()) {
              return alliance.get() == DriverStation.Alliance.Red;
            }
            return false;
          }, 
          driveSubsystem
        );
        
        configureBindings();
      }
    
      private void configureBindings() {
        driveSubsystem.setDefaultCommand(new DriveCommand(joy));
        outtake.setDefaultCommand(new FlywheelCommand(joy));
        algae.setDefaultCommand(new AlgaeCommand(joy));
      }
    
      public Command getAutonomousCommand() {
        return new Auto();
      }
    
      public static DriveSubsystem getDriveSubsystem() {
          return driveSubsystem;
      }
  
      public static FlywheelsSubsystem getOuttake() {
          return outtake;
      }

      public static AlgaeSubsystem getAlgae() {
          return algae;
      }
}
