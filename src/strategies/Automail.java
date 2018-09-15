/**
 * Project Group 23
 */

package strategies;

import automail.*;
import exceptions.InvalidRobotTypeException;

import java.util.List;

/**
 * The type Automail creates the requested robots.
 */
public class Automail {
	      
    public DelivererBehaviour[] robot;
    public IMailPool mailPool;

    // Can't handle packages heavier than the weak robot limit
    public static final boolean WEAK= false;

    // Can handle packages of any weight
    public static final boolean STRONG = true;

    /**
     * Instantiates a new Automail.
     *
     * @param mailPool     the mail pool
     * @param delivery     the delivery
     * @param robotsToMake the robots to make
     * @throws InvalidRobotTypeException the invalid robot type exception
     */
    public Automail(IMailPool mailPool, IMailDelivery delivery,
                    List<Simulation.RobotType> robotsToMake) throws
            InvalidRobotTypeException {
    	    	
    	/** Initialize the MailPool */
    	this.mailPool = mailPool;

    	/** Initialize robots */
    	robot = new DelivererBehaviour[robotsToMake.size()];
        for (int i = 0; i < robotsToMake.size(); i++) {
            robot[i] = makeRobot(robotsToMake.get(i), mailPool, delivery);
        }

    }

    /**
     * Make the requested deliverers.
     *
     * @param robotType the robot type to make
     * @param mailPool  the mail pool
     * @param delivery  the delivery report
     * @return the deliverer
     * @throws InvalidRobotTypeException it is thrown if a requested robot does
     * not exist
     */
    public DelivererBehaviour makeRobot(Simulation.RobotType robotType,
                                        IMailPool mailPool,
                                        IMailDelivery delivery)
            throws InvalidRobotTypeException {

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
