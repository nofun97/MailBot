package automail;

import strategies.IMailPool;

public class StrongRobot extends Robot{
    /**
     * Initiates the robot's location at the start to be at the mailroom
     * also set it to be waiting for mail.
     *
     * @param delivery governs the final delivery
     * @param mailPool is the source of mail items
     */
    public StrongRobot(IMailDelivery delivery, IMailPool mailPool) {
        super(delivery, mailPool, true);
    }
}
