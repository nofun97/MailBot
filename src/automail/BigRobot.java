package automail;

import exceptions.ExcessiveDeliveryException;
import exceptions.FragileItemBrokenException;
import exceptions.ItemTooHeavyException;
import strategies.IMailPool;

public class BigRobot extends Robot{
    /**
     * Initiates the robot's location at the start to be at the mailroom
     * also set it to be waiting for mail.
     *
     * @param delivery governs the final delivery
     * @param mailPool is the source of mail items
     * @param strong   is whether the robot can carry heavy items
     */
    public BigRobot(IMailDelivery delivery, IMailPool mailPool) {
        super(delivery, mailPool, true);
        setMaxItems(6);
        getTube().setTubeSize(getMaxItems());
    }

}
