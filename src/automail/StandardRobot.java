package automail;

import strategies.Automail;
import strategies.IMailPool;

public class StandardRobot extends Robot{
    /**
     * Initiates the robot's location at the start to be at the mailroom
     * also set it to be waiting for mail.
     *
     * @param delivery governs the final delivery
     * @param mailPool is the source of mail items
     */
    public StandardRobot(IMailDelivery delivery, IMailPool mailPool) {
        super(delivery, mailPool, Automail.STRONG);
    }
}
