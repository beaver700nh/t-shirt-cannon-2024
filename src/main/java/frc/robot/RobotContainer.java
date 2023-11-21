package frc.robot;

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

public class RobotContainer {
  private final MotorControllerGroup m_motorGroupL = new MotorControllerGroup(new WPI_VictorSPX(3), new WPI_VictorSPX(4));
  private final MotorControllerGroup m_motorGroupR = new MotorControllerGroup(new WPI_VictorSPX(1), new WPI_VictorSPX(2));

  private final DriveSubsystem m_driveSubsystem = new DriveSubsystem(
    new DriveConfig(m_motorGroupL, false, 0.5, 0.3),
    new DriveConfig(m_motorGroupR, true, 0.5, 0.3)
  );

  private final PneumaticsSubsystem m_pneumaticsSubsystem = new PneumaticsSubsystem();

  private final ArmSubsystem m_armSubsystem = new ArmSubsystem(new WPI_VictorSPX(8));

  private final CommandXboxController m_driverController = new CommandXboxController(OperatorConstants.kDriverControllerPort);

  private final DriveCommand m_driveCommand = new DriveCommand(
    m_driveSubsystem,
    m_driverController::getLeftY,
    m_driverController::getRightX,
    () -> 0, // m_driverController::getLeftTriggerAxis,
    () -> 0  // m_driverController::getRightTriggerAxis
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
