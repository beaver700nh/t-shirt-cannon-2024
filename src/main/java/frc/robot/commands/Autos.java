package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.Commands;

/*
 * Factory for autonomous commands.
 */
public final class Autos {
  /**
   * Auto factory to satisfy FRC.
   *
   * @return A command that does nothing.
   */
  public static CommandBase auto() {
    return Commands.none();
  }

  private Autos() {
    throw new UnsupportedOperationException("This is a utility class!");
  }
}
