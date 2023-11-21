package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ArmSubsystem extends SubsystemBase {
  private final MotorController m_motorController;

  public ArmSubsystem(MotorController motorController) {
    m_motorController = motorController;
    m_motorController.set(0);
  }

  public void setTiltSpeed(double amount) {
    m_motorController.set(amount);
  }
}
