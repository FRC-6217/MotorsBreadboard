package frc.robot.commands;

import frc.robot.subsystems.Motors;
import edu.wpi.first.wpilibj2.command.Command;

/** An command to toggle motors. */
public class ToggleMotor extends Command {
  @SuppressWarnings("PMD.UnusedPrivateField")
  Motors motors;
  /**
   * Creates a new ToggleMotor Command.
   */
  public ToggleMotor(char button) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(motors);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return true;
  }
}
