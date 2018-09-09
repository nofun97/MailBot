package automail;

import strategies.IMailPool;

public class StandardRobot extends Robot {


    /**
     * Initiates the robot's location at the start to be at the mailroom
     * also set it to be waiting for mail.
     *
     * @param delivery governs the final delivery
     * @param mailPool is the source of mail items
     * @param strong   is whether the robot can carry heavy items
     */
    public StandardRobot(IMailDelivery delivery, IMailPool mailPool, boolean strong) {
        super(delivery, mailPool, strong);
    }
}
