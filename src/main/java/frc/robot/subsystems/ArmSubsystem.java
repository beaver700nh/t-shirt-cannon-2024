package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class ArmSubsystem extends SubsystemBase {
  private final MotorController m_motorController;

  private boolean tiltInProgress = false;

  public ArmSubsystem(MotorController motorController) {
    m_motorController = motorController;
    m_motorController.set(0);
  }

  public CommandBase tiltUpCommand() {
    return new SequentialCommandGroup(
      runOnce(() -> System.out.println("Tilt Up is not implemented!"))
    );
  }

  public CommandBase tiltDownCommand() {
    return new SequentialCommandGroup(
      runOnce(() -> System.out.println("Tilt Down is not implemented!"))
    );
  }
}
