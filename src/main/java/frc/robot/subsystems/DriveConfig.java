package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.MotorController;

public class DriveConfig {
  public final MotorController controller;
  public final boolean invert;
  public final double maxAccel;

  /**
   * @param controller The motor controller to use.
   * @param invert Whether the motor should spin in reverse.
   * @param maxAccel The maximum acceleration of the motor.
   */
  public DriveConfig(MotorController controller, boolean invert, double maxAccel) {
    this.controller = controller;
    this.invert = invert;
    this.maxAccel = maxAccel;

    controller.setInverted(invert);
  }

  /**
   * Accelerates the motor towards the given velocity.
   * Returns the actual velocity of the motor.
   *
   * @param velocity The requested velocity of the motor.
   * @return The actual velocity of the motor.
   */
  public double accelTo(double velocity) {
    double now = controller.get();

    if (velocity > now) {
      now = Math.min(velocity, now + maxAccel);
    }
    else if (velocity < now) {
      now = Math.max(velocity, now - maxAccel);
    }

    controller.set(now);
    return now;
  }
}
