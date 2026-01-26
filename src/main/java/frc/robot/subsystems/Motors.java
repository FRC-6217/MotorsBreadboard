// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix6.hardware.TalonFX; // For Falcon 500
import com.ctre.phoenix6.controls.DutyCycleOut;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants.MotorConstants;

public class Motors extends SubsystemBase {
  private TalonFX motorA;
  private TalonFX motorB;
  private SparkMax motorX;
  private SparkMax motorY;
  private int speedA = 0;
  private int speedB = 0;
  private int speedX = 0;
  private int speedY = 0;
  private boolean runningA = false;
  private boolean runningB = false;
  private boolean runningX = false;
  private boolean runningY = false;
  private String smartDashboadMotorA = "Kraken Motor Output A";
  private String smartDashboadMotorB = "Kraken Motor Output B";
  private String smartDashboadMotorX = "NEO Motor Output X";
  private String smartDashboadMotorY = "NEO Motor Output Y";

  /** Creates a new Subsystem. */
  public Motors() {
      motorA = new TalonFX(MotorConstants.kMotorACANId);
      motorB = new TalonFX(MotorConstants.kMotorBCANId);
      motorX = new SparkMax(MotorConstants.kMotorXCANId, MotorType.kBrushless);
      motorY = new SparkMax(MotorConstants.kMotorYCANId, MotorType.kBrushless);

      SmartDashboard.putNumber(smartDashboadMotorA, MotorConstants.initialMotorSpeed);
      SmartDashboard.putNumber(smartDashboadMotorB, MotorConstants.initialMotorSpeed);
      SmartDashboard.putNumber(smartDashboadMotorX, MotorConstants.initialMotorSpeed);
      SmartDashboard.putNumber(smartDashboadMotorY, MotorConstants.initialMotorSpeed);
  }

  public void toggleMotor (char motor) {
    if (motor == 'A') {
      runningA = !runningA;
    }
    if (motor == 'B') {
      runningB = !runningB;
    }
    if (motor == 'X') {
      runningX = !runningX;
    }
    if (motor == 'Y') {
      runningY = !runningY;
    }
  }

  @Override
  public void periodic() {
      // Get speed from dashboard and run/stop motors
      if (runningA) {
        SmartDashboard.getNumber(smartDashboadMotorA, speedA);
        motorA.setControl(new DutyCycleOut(speedA));
      }
      else {
        motorA.setControl(new DutyCycleOut(0));
      }
      if (runningB) {
        SmartDashboard.getNumber(smartDashboadMotorB, speedB);
        motorB.setControl(new DutyCycleOut(speedB));
      }
      else {
        motorB.setControl(new DutyCycleOut(0));
      }
      if (runningX) {
        SmartDashboard.getNumber(smartDashboadMotorX, speedX);
        motorX.set(speedX);
      }
      else {
        motorX.set(0);
      }
      if (runningY) {
        SmartDashboard.getNumber(smartDashboadMotorY, speedY);
        motorX.set(speedY);
      }
      else {
        motorY.set(0);
      }
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
