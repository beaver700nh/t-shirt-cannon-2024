package frc.robot;

import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.Autos;
import frc.robot.commands.DriveCommand;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.DriveConfig;
import frc.robot.subsystems.PneumaticsSubsystem;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

public class RobotContainer {
  private final MotorControllerGroup m_motorGroupL = new MotorControllerGroup(new WPI_VictorSPX(3), new WPI_VictorSPX(4));
  private final MotorControllerGroup m_motorGroupR = new MotorControllerGroup(new WPI_VictorSPX(1), new WPI_VictorSPX(2));

  private final DriveSubsystem m_driveSubsystem = new DriveSubsystem(
    new DriveConfig(m_motorGroupL, false, 0.5, 0.3),
    new DriveConfig(m_motorGroupR, true, 0.5, 0.3)
  );

  private final PneumaticsSubsystem m_pneumaticsSubsystem = new PneumaticsSubsystem();

  private final CommandXboxController m_driverController = new CommandXboxController(OperatorConstants.kDriverControllerPort);

  private final DriveCommand m_driveCommand = new DriveCommand(
    m_driveSubsystem,
    m_driverController::getLeftY,
    m_driverController::getRightX,
    m_driverController::getLeftTriggerAxis,
    m_driverController::getRightTriggerAxis
  );

  private final Command m_launchCommand = new SequentialCommandGroup(
    new InstantCommand(m_pneumaticsSubsystem::launch, m_pneumaticsSubsystem),
    new WaitCommand(0.125),
    new InstantCommand(m_pneumaticsSubsystem::stopLaunch, m_pneumaticsSubsystem)
  );

  public RobotContainer() {
    m_driveSubsystem.setDefaultCommand(m_driveCommand);

    configureBindings();
  }

  private void configureBindings() {
    m_driverController.a().onTrue(m_launchCommand);
  }

  public Command getAutonomousCommand() {
    return Autos.auto();
  }
}
