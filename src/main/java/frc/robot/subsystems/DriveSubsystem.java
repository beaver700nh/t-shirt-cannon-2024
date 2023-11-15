// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

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
   * Sets the drive train velocities immediately to the given values.
   *
   * @param velocityL
   * @param velocityR
   */
  public void forceTo(double velocityL, double velocityR) {
    m_configL.controller.set(velocityL);
    m_configR.controller.set(velocityR);
  }

  /**
   * Requests the drive train to drive at the given velocities.
   *
   * @param velocityL The requested velocity of the left drive motor(s).
   * @param velocityR The requested velocity of the right drive motor(s).
   */
  public void accelTo(double velocityL, double velocityR) {
    m_configL.accelTo(velocityL);
    m_configR.accelTo(velocityR);
  }
}
