package strategies;

import automail.*;
import exceptions.InvalidRobotTypeException;

import java.util.List;

public class Automail {
	      
    public RobotBehaviour[] robot;
    public IMailPool mailPool;
    public final boolean weak = false;  // Can't handle more than 2000 grams
    public final boolean strong = true; // Can handle any weight that arrives

    // at
    // the
    // building


    public Automail(IMailPool mailPool, IMailDelivery delivery,
                    List<Simulation.RobotType> robotsToMake) throws InvalidRobotTypeException {
    	// Swap between simple provided strategies and your strategies here
    	    	
    	/** Initialize the MailPool */
    	
    	this.mailPool = mailPool;
    	
        /** Initialize the RobotAction */

    	/** Initialize robots */
    	robot = new RobotBehaviour[robotsToMake.size()];
        for (int i = 0; i < robotsToMake.size(); i++) {
            robot[i] = makeRobot(robotsToMake.get(i), mailPool, delivery);
        }

    }

    public RobotBehaviour makeRobot(Simulation.RobotType robotType,
                                    IMailPool mailPool, IMailDelivery delivery) throws InvalidRobotTypeException {
        switch(robotType){
            case Big:
                return new BigRobot(delivery, mailPool);
            case Weak:
                return new WeakRobot(delivery, mailPool);
            case Careful:
                return new CarefulRobot(delivery, mailPool);
            case Standard:
                return new StandardRobot(delivery, mailPool);
            default:
                throw new InvalidRobotTypeException();
        }
    }
}
