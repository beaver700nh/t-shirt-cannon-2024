package frc.robot;

/*
 * Namespaces for constants used throughout code base.
 */
public final class Constants {
  public static class OperatorConstants {
    public static final int kDriverControllerPort = 0;
  }

  public static class Drive {
    public static final double kMinSpeed = 0.4;
    public static final double kMinTurn = 0.2;
    public static final double kRateSpinUp = 0.5;
    public static final double kRateSpinDown = 0.3;

    public static class CAN {
      public static final int kMotorL1 = 3;
      public static final int kMotorL2 = 4;
      public static final int kMotorR1 = 1;
      public static final int kMotorR2 = 2;
    }
  }

  public static class Arm {
    public static final double kMultiplierTilt = 0.6;

    public static class CAN {
      public static final int kMotorTilt = 8;
    }
  }
}
