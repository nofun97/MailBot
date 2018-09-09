package strategies;

import automail.*;

public class Automail {
	      
    public Robot[] robot;
    public IMailPool mailPool;
    public final boolean weak = false;  // Can't handle more than 2000 grams
    public final boolean strong = true; // Can handle any weight that arrives
    // at
    // the
    // building


    public Automail(IMailPool mailPool, IMailDelivery delivery) {
    	// Swap between simple provided strategies and your strategies here
    	    	
    	/** Initialize the MailPool */
    	
    	this.mailPool = mailPool;
    	
        /** Initialize the RobotAction */

    	/** Initialize robots */
    	robot = new Robot[4];
    	robot[0] = new WeakRobot(delivery, mailPool);
    	robot[1] = new StandardRobot(delivery, mailPool);
        robot[2] = new CarefulRobot(delivery, mailPool);
        robot[3] = new BigRobot(delivery, mailPool);
    }
    
}
