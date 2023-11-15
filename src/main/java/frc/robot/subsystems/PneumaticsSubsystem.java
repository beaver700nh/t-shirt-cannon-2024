package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class PneumaticsSubsystem extends SubsystemBase {
  private final Solenoid m_solenoid = new Solenoid(PneumaticsModuleType.CTREPCM, 0);

  public PneumaticsSubsystem() {
    m_solenoid.set(false);
  }

  public void launch() {
    System.out.println("Launching!");
    m_solenoid.set(true);
  }

  public void stopLaunch() {
    m_solenoid.set(false);
    System.out.println("... launch stopped.");
  }
}
