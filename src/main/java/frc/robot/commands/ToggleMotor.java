package frc.robot.commands;

import frc.robot.subsystems.Motors;
import edu.wpi.first.wpilibj2.command.Command;

/** An command to toggle motors. */
public class ToggleMotor extends Command {
  @SuppressWarnings("PMD.UnusedPrivateField")
  private Motors motors;
  char motor;
  /**
   * Creates a new ToggleMotor Command.
   */
  public ToggleMotor(Motors motors, char buttonPressed) {
    this.motors = motors;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(motors);
    motor = buttonPressed;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    motors.toggleMotor(motor);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return true;
  }
}
