package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class PneumaticsSubsystem extends SubsystemBase {
  private final Solenoid m_solenoid = new Solenoid(PneumaticsModuleType.CTREPCM, 0);

  public PneumaticsSubsystem() {
    m_solenoid.set(false);
  }

  /**
   * Opens valve to release pressure into barrel.
   */
  private void launch() {
    System.out.println("Launching!");
    m_solenoid.set(true);
  }

  /**
   * Closes barrel pressure valve.
   */
  private void stopLaunch() {
    m_solenoid.set(false);
    System.out.println("... launch stopped.");
  }

  /**
   * Generate a command that launches the ball for 1/8 second.
   *
   * @return The launch command.
   */
  public CommandBase launchCommand() {
    return new SequentialCommandGroup(
      runOnce(this::launch),
      new WaitCommand(0.125),
      runOnce(this::stopLaunch)
    );
  }
}
