package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

/*
 * Subsystem to control the arm motor.
 */
public class ArmSubsystem extends SubsystemBase {
  private final MotorController m_motorController;

  /**
   * @param motorController The controller for the arm tilt motor.
   */
  public ArmSubsystem(MotorController motorController) {
    m_motorController = motorController;
    m_motorController.set(0);
  }

  /**
   * Sets the velocity of the tilt motor.
   *
   * @param amount The requested tilt speed.
   */
  public void setTiltSpeed(double amount) {
    m_motorController.set(amount);
  }
}
