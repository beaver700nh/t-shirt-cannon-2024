// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.util.function.DoubleSupplier;

import frc.robot.subsystems.DriveSubsystem;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class DriveCommand extends CommandBase {
  private final DriveSubsystem m_drive;
  private final DoubleSupplier m_inputL, m_inputR;

  /**
   * @param drive The drive subsystem to use.
   * @param inputL A supplier of the left drive input.
   * @param inputR A supplier of the right drive input.
   */
  public DriveCommand(DriveSubsystem drive, DoubleSupplier inputL, DoubleSupplier inputR) {
    m_drive = drive;
    m_inputL = inputL;
    m_inputR = inputR;

    addRequirements(drive);
  }

  @Override
  public void initialize() {
    m_drive.forceTo(0, 0);
  }

  @Override
  public void execute() {
    m_drive.accelTo(m_inputL.getAsDouble(), m_inputR.getAsDouble());
  }

  @Override
  @SuppressWarnings("PMD.UnusedFormalParameter")
  public void end(boolean interrupted) {
    m_drive.forceTo(0, 0);
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
