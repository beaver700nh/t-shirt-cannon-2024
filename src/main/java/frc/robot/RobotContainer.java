package frc.robot;

import frc.robot.Constants;
import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.Autos;
import frc.robot.commands.DriveCommand;
import frc.robot.commands.ArmCommand;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.DriveConfig;
import frc.robot.subsystems.PneumaticsSubsystem;
import frc.robot.subsystems.ArmSubsystem;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

/*
 * The main container class for the robot.
 * Most high-level or important stuff goes here.
 */
public class RobotContainer {
  private final CommandXboxController m_driverController = new CommandXboxController(OperatorConstants.kDriverControllerPort);

  private final DriveSubsystem m_driveSubsystem = new DriveSubsystem(
    new DriveConfig(
      new MotorControllerGroup(
        new WPI_VictorSPX(Constants.Drive.CAN.kMotorL1),
        new WPI_VictorSPX(Constants.Drive.CAN.kMotorL2)
      ),
      false, Constants.Drive.kRateSpinUp, Constants.Drive.kRateSpinDown
    ),
    new DriveConfig(
      new MotorControllerGroup(
        new WPI_VictorSPX(Constants.Drive.CAN.kMotorR1),
        new WPI_VictorSPX(Constants.Drive.CAN.kMotorR2)
      ),
      true, Constants.Drive.kRateSpinUp, Constants.Drive.kRateSpinDown
    )
  );

  private final PneumaticsSubsystem m_pneumaticsSubsystem = new PneumaticsSubsystem();

  private final ArmSubsystem m_armSubsystem = new ArmSubsystem(
    new WPI_VictorSPX(Constants.Arm.CAN.kMotorTilt)
  );

  private final DriveCommand m_driveCommand = new DriveCommand(
    m_driveSubsystem,
    m_driverController::getLeftY,
    m_driverController::getRightX,
    // [Note] Damping controls are disabled.
    () -> 1.0, // m_driverController::getLeftTriggerAxis,
    () -> 1.0  // m_driverController::getRightTriggerAxis
  );

  private final ArmCommand m_armCommand = new ArmCommand(
    m_armSubsystem,
    m_driverController::getLeftTriggerAxis,
    m_driverController::getRightTriggerAxis
  );

  public RobotContainer() {
    m_driveSubsystem.setDefaultCommand(m_driveCommand);
    m_armSubsystem.setDefaultCommand(m_armCommand);

    configureBindings();
  }

  private void configureBindings() {
    m_driverController.a().debounce(2).onTrue(m_pneumaticsSubsystem.launchCommand());
  }

  public Command getAutonomousCommand() {
    return Autos.auto();
  }
}
