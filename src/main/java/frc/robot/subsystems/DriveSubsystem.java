package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DriveSubsystem extends SubsystemBase {
  private final DriveConfig m_configL, m_configR;

  /**
   * @param configL The configuration of the left drive motor(s).
   * @param configR The configuration of the right drive motor(s).
   */
  public DriveSubsystem(DriveConfig configL, DriveConfig configR) {
    m_configL = configL;
    m_configR = configR;
  }

  /**
   * Sets the robot velocity immediately using the given values.
   *
   * @param x The requested movement rate.
   * @param r The requested turning rate.
   */
  public void forceTo(double x, double r) {
    m_configL.getController().set(x - r);
    m_configL.getController().set(x + r);
  }

  /**
   * Requests the robot to drive using the given velocity values.
   *
   * @param x The requested movement rate.
   * @param r The requested turning rate.
   */
  public void accelTo(double x, double r) {
    m_configL.accelTo(x - r);
    m_configR.accelTo(x + r);
  }
}
