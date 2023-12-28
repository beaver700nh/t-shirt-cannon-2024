package frc.robot.commands;

import java.util.function.Supplier;

import frc.robot.Constants.DriveConstants;
import frc.robot.subsystems.DriveSubsystem;

import edu.wpi.first.wpilibj2.command.CommandBase;

/*
 * [Default Command]
 * Accelerates robot to velocity specified by suppliers bound in constructor.
 */
public class DriveCommand extends CommandBase {
  private final DriveSubsystem m_drive;
  private final Supplier<Double> m_inputX, m_inputR, m_inputP, m_inputQ;

  /**
   * @param drive The drive subsystem to use.
   * @param inputX A supplier of the drive velocity.
   * @param inputR A supplier of the drive turn rate.
   * @param inputP A supplier of the velocity multiplier.
   * @param inputQ A supplier of the turn rate multiplier.
   */
  public DriveCommand(
    DriveSubsystem drive,
    Supplier<Double> inputX, Supplier<Double> inputR,
    Supplier<Double> inputP, Supplier<Double> inputQ
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
   * @return Requested X value, with multiplier P in [MIN_X..1].
   */
  private double dampedX() {
    return m_inputX.get() *
      ((1 - DriveConstants.kMinSpeed) * m_inputP.get() + DriveConstants.kMinSpeed);
  }

  /**
   * @return Requested R value, with multiplier Q in [MIN_R..1].
   */
  private double dampedR() {
    return m_inputR.get() *
      ((1 - DriveConstants.kMinTurn) * m_inputQ.get() + DriveConstants.kMinTurn);
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
