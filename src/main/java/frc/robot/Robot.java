// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.TimedRobot;

/**
 * This is a sample program showing the use of the solenoid classes during operator control. Three
 * buttons from a joystick will be used to control two solenoids: One button to control the position
 * of a single solenoid and the other two buttons to control a double solenoid. Single solenoids can
 * either be on or off, such that the air diverted through them goes through either one channel or
 * the other. Double solenoids have three states: Off, Forward, and Reverse. Forward and Reverse
 * divert the air through the two channels and correspond to the on and off of a single solenoid,
 * but a double solenoid can also be "off", where the solenoid will remain in its default power off
 * state. Additionally, double solenoids take up two channels on your PCM whereas single solenoids
 * only take a single channel.
 */
public class Robot extends TimedRobot {
 
  //Base intialization
   private XboxController m_controller = new XboxController(0);
  private Joystick m_stick;
  private JoystickButton m_solenoidButton;
  private JoystickButton m_doubleSolenoidForwardButton;
  private JoystickButton m_doubleSolenoidReverseButton;

  //Compressor Intilizaiion. Intilizes Compressor default values
  Compressor phCompressor = new Compressor(1, PneumaticsModuleType.REVPH);
  phCompressor.enableDigital();
  phCompressor.disable();
  boolean enabled = phCompressor.enabled();
  boolean pressureSwitch = phCompressor.getPressureSwitchValue();
  double current = phCompressor.getCompressorCurrent();

  //More Buttons!
  private static final int kSolenoidButton = 1;
  private static final int kDoubleSolenoidForward = 2;
  private static final int kDoubleSolenoidReverse = 3;

  //Buttons!
  private final Joystick m_stick = new Joystick(0);
  m_doubleSolenoidForwardButton = new JoystickButton(m_stick, kDoubleSolenoidForward);
  m_doubleSolenoidReverseButton = new JoystickButton(m_stick, kDoubleSolenoidReverse);

  // Solenoid Declaration
  private final DoubleSolenoid m_doubleSolenoid = new DoubleSolenoid(PneumaticsModuleType.REVPH, 1, 2);

  @Override
  public void teleopPeriodic() {
    if (m_controller.getXButton()) {
        m_doubleSolenoid.set(DoubleSolenoid.Value.kForward);
    } else if (m_controller.getYButton()) {
        m_doubleSolenoid.set(DoubleSolenoid.Value.kReverse);
    }

    if (m_controller.getAButton()) {
        phCompressor.start();
    } else if (m_controller.getBButton()) {
        phCompressor.stop();
    }
  }
}
