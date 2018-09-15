package strategies;

import automail.*;
import exceptions.InvalidRobotTypeException;

import java.util.List;

public class Automail {
	      
    public DelivererBehaviour[] robot;
    public IMailPool mailPool;
    // Can't handle more than packages heavier than the weak robot limit
    public static final boolean WEAK= false;

    // Can handle packages of any weight
    public static final boolean STRONG = true;

    public Automail(IMailPool mailPool, IMailDelivery delivery,
                    List<Simulation.RobotType> robotsToMake) throws InvalidRobotTypeException {
    	    	
    	/** Initialize the MailPool */
    	this.mailPool = mailPool;

    	/** Initialize robots */
    	robot = new DelivererBehaviour[robotsToMake.size()];
        for (int i = 0; i < robotsToMake.size(); i++) {
            robot[i] = makeRobot(robotsToMake.get(i), mailPool, delivery);
        }

    }

    public DelivererBehaviour makeRobot(Simulation.RobotType robotType,
                                        IMailPool mailPool, IMailDelivery delivery) throws InvalidRobotTypeException {

        // create robots based on the designated type
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
