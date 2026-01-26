// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix6.hardware.TalonFX; // For Falcon 500
import com.ctre.phoenix6.controls.DutyCycleOut;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants.MotorContants;

public class Motors extends SubsystemBase {
  TalonFX motorA;
  TalonFX motorB;
  int speedA;
  int speedB;

  /** Creates a new Subsystem. */
  public Motors() {
      motorA = new TalonFX(MotorContants.kMotorACANId);
      motorB = new TalonFX(MotorContants.kMotorBCANId);
  }

  @Override
  public void periodic() {
      // Display speed on dashboard
      SmartDashboard.getNumber("Kraken Motor Output A", speedA);
      SmartDashboard.getNumber("Kraken Motor Output B", speedB);
      motorA.setControl(new DutyCycleOut(speedA));
      motorB.setControl(new DutyCycleOut(speedB));
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
