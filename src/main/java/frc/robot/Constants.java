// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
  public static class OperatorConstants {
    public static final int kDriverControllerPort = 0;
    public static final double debounceTime = 0.1;
  }
  public static class MotorConstants {
    public static final int kMotorACANId = 15;
    public static final int kMotorBCANId = 16;
    public static final int kMotorXCANId = 22;
    public static final int kMotorYCANId = 23;

    public static final double initialMotorSpeed = 0.25;

    // Talon FX PID Constants
    public static final double kS_talonFx = 0.10; // Add 0.1 V output to overcome static friction
    public static final double kV_talonFx = 0.12; // A velocity target of 1 rps results in 0.12 V output
    public static final double kP_talonFx = 0.11; // An error of 1 rps results in 0.11 V output
    public static final double kI_talonFx = 0.00; // no output for integrated error
    public static final double kD_talonFx = 0.00; // no output for error derivative
    public static final double feedForward_talonFx = 0.50; // 0.5 V to overcome gravity
    public static final double maxRPS_talonFx = 9.0; // 5,400 RPM based on 6,000 RPM max for X60, could be based on 7,350 RPM max for X44
  }
}
