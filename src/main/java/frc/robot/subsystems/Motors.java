// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix6.hardware.TalonFX; // For Falcon 500
import com.ctre.phoenix6.configs.Slot0Configs;
import com.ctre.phoenix6.controls.DutyCycleOut;
import com.ctre.phoenix6.controls.VelocityVoltage;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.RelativeEncoder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants.MotorConstants;

public class Motors extends SubsystemBase {
  private TalonFX motorA;
  private TalonFX motorB;
  private SparkMax motorX;
  private SparkMax motorY;
  private RelativeEncoder encoderX;
  private RelativeEncoder encoderY;
  private double speedA = 0;
  private double speedB = 0;
  private double speedX = 0;
  private double speedY = 0;
  private boolean runningA = false;
  private boolean runningB = false;
  private boolean runningX = false;
  private boolean runningY = false;
  private String smartDashboadMotorA = "Kraken Motor Output A";
  private String smartDashboadMotorB = "Kraken Motor Output B";
  private String smartDashboadMotorX = "NEO Motor Output X";
  private String smartDashboadMotorY = "NEO Motor Output Y";
  private String smartDashboadMotorA_V = "Kraken Motor Velocity A";
  private String smartDashboadMotorB_V = "Kraken Motor Velocity B";
  private String smartDashboadMotorX_V = "NEO Motor Velocity X";
  private String smartDashboadMotorY_V = "NEO Motor Velocity Y";
  private String smartDashboadMotorToggled = "Motor Toggled";
  private VelocityVoltage velocityRequest;

  /** Creates a new Subsystem. */
  public Motors() {
    // Create motor variables
    motorA = new TalonFX(MotorConstants.kMotorACANId);
    motorB = new TalonFX(MotorConstants.kMotorBCANId);
    motorX = new SparkMax(MotorConstants.kMotorXCANId, MotorType.kBrushless);
    motorY = new SparkMax(MotorConstants.kMotorYCANId, MotorType.kBrushless);
    encoderX = motorX.getEncoder();
    encoderY = motorY.getEncoder();

    // Initialize the SmartDashboard for requested speeds
    SmartDashboard.putNumber(smartDashboadMotorA, MotorConstants.initialMotorSpeed);
    SmartDashboard.putNumber(smartDashboadMotorB, MotorConstants.initialMotorSpeed);
    SmartDashboard.putNumber(smartDashboadMotorX, MotorConstants.initialMotorSpeed);
    SmartDashboard.putNumber(smartDashboadMotorY, MotorConstants.initialMotorSpeed);

    // Configure TalonFX PID Control
    var slot0Configs = new Slot0Configs();
    slot0Configs.kS = MotorConstants.kS_talonFx;
    slot0Configs.kV = MotorConstants.kV_talonFx;
    slot0Configs.kP = MotorConstants.kP_talonFx;
    slot0Configs.kI = MotorConstants.kI_talonFx;
    slot0Configs.kD = MotorConstants.kD_talonFx;
    motorA.getConfigurator().apply(slot0Configs);
    motorB.getConfigurator().apply(slot0Configs);

    // create a velocity closed-loop request, voltage output, slot 0 configs
    velocityRequest = new VelocityVoltage(0).withSlot(0);
  }

  public void toggleMotor (char motor) {
    SmartDashboard.putString(smartDashboadMotorToggled, String.valueOf(motor));
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
    SmartDashboard.putBoolean("Running A", runningA);;
    SmartDashboard.putBoolean("Running B", runningB);;
    SmartDashboard.putBoolean("Running X", runningX);;
    SmartDashboard.putBoolean("Running Y", runningY);;

      // Get speed from dashboard and run/stop motors
      // A Button Motor - Kraken 
      if (runningA) {
        speedA = SmartDashboard.getNumber(smartDashboadMotorA, MotorConstants.initialMotorSpeed);
        /* 
        motorA.setControl(new DutyCycleOut(speedA));
        SmartDashboard.putString("Running Motor Speed", "A: " + String.valueOf(speedA));
        */
        // Use PID Control
        // set velocity based on max speed and requested percentage
        motorA.setControl(velocityRequest.withVelocity(MotorConstants.maxRPS_talonFx * speedA).withFeedForward(MotorConstants.feedForward_talonFx));
        SmartDashboard.putString("Running Motor Speed", "A: " + String.valueOf(MotorConstants.maxRPS_talonFx * speedA));
      }
      else {
        motorA.setControl(new DutyCycleOut(0));
        /* 
        // Use PID Control
        motorA.setControl(velocityRequest.withVelocity(0.0).withFeedForward(MotorConstants.feedForward_talonFx));
        */
      }
      // B Button Motor - Kraken
      if (runningB) {
        speedB = SmartDashboard.getNumber(smartDashboadMotorB, MotorConstants.initialMotorSpeed);
        /* 
        motorB.setControl(new DutyCycleOut(speedB));
        SmartDashboard.putString("Running Motor Speed", "B: " + String.valueOf(speedB));
        */
        // Use PID Control
        // set velocity based on max speed and requested percentage
        motorB.setControl(velocityRequest.withVelocity(MotorConstants.maxRPS_talonFx * speedB).withFeedForward(MotorConstants.feedForward_talonFx));
        SmartDashboard.putString("Running Motor Speed", "B: " + String.valueOf(MotorConstants.maxRPS_talonFx * speedB));
      }
      else {
        motorB.setControl(new DutyCycleOut(0));
        /* 
        // Use PID Control
        motorB.setControl(velocityRequest.withVelocity(0.0).withFeedForward(MotorConstants.feedForward_talonFx));
        */
      }
      // X Button Motor - NEO 
      if (runningX) {
        speedX = SmartDashboard.getNumber(smartDashboadMotorX, MotorConstants.initialMotorSpeed);
        motorX.set(speedX);
      }
      else {
        motorX.set(0);
      }
      // Y Button Motor - NEO
      if (runningY) {
        speedY = SmartDashboard.getNumber(smartDashboadMotorY, MotorConstants.initialMotorSpeed);
        motorX.set(speedY);
      }
      else {
        motorY.set(0);
      }
  } 

  @Override
  public void periodic() {
      // Display motor current speed in RPM
      SmartDashboard.putNumber(smartDashboadMotorA_V, motorA.getVelocity().getValueAsDouble() * 60);
      SmartDashboard.putNumber(smartDashboadMotorB_V, motorB.getVelocity().getValueAsDouble() * 60);
      SmartDashboard.putNumber(smartDashboadMotorX_V, encoderX.getVelocity());
      SmartDashboard.putNumber(smartDashboadMotorY_V, encoderY.getVelocity());
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
