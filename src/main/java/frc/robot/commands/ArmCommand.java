package frc.robot.commands;

import java.util.function.DoubleSupplier;

import frc.robot.subsystems.ArmSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class ArmCommand extends CommandBase {
  private final ArmSubsystem m_arm;
  private final DoubleSupplier m_inputM, m_inputP;
  private final double MULTIPLIER = 0.6;

  public ArmCommand(ArmSubsystem arm, DoubleSupplier inputM, DoubleSupplier inputP) {
    m_arm = arm;
    m_inputM = inputM;
    m_inputP = inputP;

    addRequirements(arm);
  }

  @Override
  public void initialize() {
    m_arm.setTiltSpeed(0);
  }

  @Override
  public void execute() {
    m_arm.setTiltSpeed(
      MULTIPLIER * m_inputP.getAsDouble() - 
      MULTIPLIER * m_inputM.getAsDouble()
    );
  }

  @Override
  @SuppressWarnings("PMD.UnusedFormalParameter")
  public void end(boolean interrupted) {
    m_arm.setTiltSpeed(0);
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
