
package org.oastem.frc.mecanum;


import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;

/**
 * This is a demo program showing the use of the RobotDrive class.
 * The SampleRobot class is the base of a robot application that will automatically call your
 * Autonomous and OperatorControl methods at the right time as controlled by the switches on
 * the driver station or the field controls.
 *
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the SampleRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 *
 * WARNING: While it may look like a good choice to use for your code if you're inexperienced,
 * don't. Unless you know what you are doing, complex code will be much more difficult under
 * this system. Use IterativeRobot or Command-Based instead if you're new.
 */
public class Robot extends SampleRobot {
    
	RobotDrive mecanum;
    Joystick stickRig;
    Joystick stickLef;

    private static final int FRONT_RIGHT_MECANUM = 0;
    private static final int BACK_RIGHT_MECANUM = 1;
    private static final int FRONT_LEFT_MECANUM = 2;
    private static final int BACK_LEFT_MECANUM = 3;
    
    private static final int ROTATE_CLOCK_BUTT = 5;
    private static final int ROTATE_COUNT_BUTT = 4;
    
    
    public Robot() {
        mecanum = new RobotDrive(FRONT_LEFT_MECANUM, BACK_LEFT_MECANUM, FRONT_RIGHT_MECANUM, BACK_RIGHT_MECANUM);
        stickRig = new Joystick(0);
        stickLef = new Joystick(1);
    }

    /**
     * Drive left & right motors for 2 seconds then stop
     */
    public void autonomous() {
    	
    }

    /**.g
     * Runs the motors with arcade steering.
     */
    public void operatorControl() {
        while (isOperatorControl() && isEnabled()) {
            mecanum.tankDrive(-stickLef.getY(), -stickRig.getY());
        	//mecanum.mecanumDrive_Cartesian(stick.getX(), stick.getY(), getRotation(), 0); 
        }
    }
    
    private double getRotation()
    {
    	if (stickRig.getRawButton(ROTATE_CLOCK_BUTT))
    		return scaleZ(stickRig.getZ());
    	else if (stickRig.getRawButton(ROTATE_COUNT_BUTT))
    		return -scaleZ(stickRig.getZ());
    	return 0;
    }

    private double scaleZ(double rawZ) {
		return Math.min(1.0, 0.5 - 0.3 * rawZ);
	}
    
    /**
     * Runs during test mode
     */
    public void test() {
    }
}
