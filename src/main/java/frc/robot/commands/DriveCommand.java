package frc.robot.commands;

import java.util.function.DoubleSupplier;

import frc.robot.subsystems.DriveSubsystem;

import edu.wpi.first.wpilibj2.command.CommandBase;

/*
 * [Default Command]
 * Accelerates robot to velocity specified by suppliers bound in constructor.
 */
public class DriveCommand extends CommandBase {
  private final DriveSubsystem m_drive;
  private final DoubleSupplier m_inputX, m_inputR, m_inputP, m_inputQ;
  private final double MIN_X = 0.4, MIN_R = 0.2;

  /**
   * @param drive The drive subsystem to use.
   * @param inputX A supplier of the drive velocity.
   * @param inputR A supplier of the drive turn rate.
   * @param inputP A supplier of the velocity multiplier.
   * @param inputQ A supplier of the turn rate multiplier.
   */
  public DriveCommand(
    DriveSubsystem drive,
    DoubleSupplier inputX, DoubleSupplier inputR,
    DoubleSupplier inputP, DoubleSupplier inputQ
  ) {
    m_drive = drive;
    m_inputX = inputX;
    m_inputR = inputR;
    m_inputP = inputP;
    m_inputQ = inputQ;

    addRequirements(drive);
  }

  @Override
  public void initialize() {
    m_drive.forceTo(0, 0);
  }

  @Override
  public void execute() {
    m_drive.accelTo(dampedX(), dampedR());
  }

  /**
   * @return Requested X value, with damping P in [MIN_X..1].
   */
  private double dampedX() {
    return m_inputX.getAsDouble() * ((1 - MIN_X) * m_inputP.getAsDouble() + MIN_X);
  }

  /**
   * @return Requested R value, with damping Q in [MIN_R..1].
   */
  private double dampedR() {
    return m_inputR.getAsDouble() * ((1 - MIN_R) * m_inputQ.getAsDouble() + MIN_R);
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
