// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DriveSubsystem extends SubsystemBase {
  private final DriveConfig m_configL, m_configR;
  private DoubleSupplier m_inputL, m_inputR;

  public DriveSubsystem(DriveConfig configL, DriveConfig configR) {
    m_configL = configL;
    m_configR = configR;
  }

  public void attachController(DoubleSupplier inputL, DoubleSupplier inputR) {
    m_inputL = inputL;
    m_inputR = inputR;
  }

  private void set(double powerL, double powerR) {
    m_configL.rampTo(powerL);
    m_configR.rampTo(powerR);
  }

  @Override
  public void periodic() {
    set(m_inputL.getAsDouble(), m_inputR.getAsDouble());
  }
}
