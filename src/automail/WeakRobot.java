/**
 * Project Group 23
 */

package automail;

import strategies.Automail;
import strategies.IMailPool;

public class WeakRobot extends Robot {

    /**
     * Initiates the robot's location at the start to be at the mailroom
     * also set it to be waiting for mail.
     *
     * @param delivery governs the final delivery
     * @param mailPool is the source of mail items
     */
    public WeakRobot(IMailDelivery delivery, IMailPool mailPool) {
        // weak robots have limits in its maximum weight
        super(delivery, mailPool, Automail.WEAK);
    }
}
