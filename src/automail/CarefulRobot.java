package automail;

import exceptions.FragileItemBrokenException;
import strategies.Automail;
import strategies.IMailPool;

public class CarefulRobot extends Robot{

    private boolean isCareful = true;
    public final int MAX_FRAGILE_ITEMS = 1;
    /**
     * Initiates the robot's location at the start to be at the mailroom
     * also set it to be waiting for mail.
     *
     * @param delivery governs the final delivery
     * @param mailPool is the source of mail items
     */
    public CarefulRobot(IMailDelivery delivery, IMailPool mailPool) {
        super(delivery, mailPool, Automail.STRONG);
    }

    @Override
    public void moveTowards(int destination) throws FragileItemBrokenException {
        if(isCareful){
            isCareful = false;
            return;
        }
        super.moveTowards(destination);
    }
}
