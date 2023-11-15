package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.MotorController;

public class DriveConfig {
  public final MotorController controller;
  public final boolean invert;
  public final double ramp;

  public DriveConfig(MotorController controller, boolean invert, double ramp) {
    this.controller = controller;
    this.invert = invert;
    this.ramp = ramp;

    controller.setInverted(invert);
  }

  public double rampTo(double power) {
    double now = controller.get();

    if (power > now) {
      now = Math.min(power, now + ramp);
    }
    else if (power < now) {
      now = Math.max(power, now - ramp);
    }

    controller.set(now);
    return now;
  }
}
