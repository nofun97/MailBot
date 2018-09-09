package automail;

import exceptions.ExcessiveDeliveryException;
import exceptions.FragileItemBrokenException;
import exceptions.ItemTooHeavyException;
import strategies.IMailPool;

public class CarefulRobot extends Robot{

    private boolean careful = true;

    /**
     * Initiates the robot's location at the start to be at the mailroom
     * also set it to be waiting for mail.
     *
     * @param delivery governs the final delivery
     * @param mailPool is the source of mail items
     */
    public CarefulRobot(IMailDelivery delivery, IMailPool mailPool) {
        super(delivery, mailPool, true);
    }
    
    @Override
    public void moveTowards(int destination) throws FragileItemBrokenException {
        if(careful){
            setCurrent_floor(getCurrent_floor());
            careful = false;
        }
        if(getCurrent_floor() < destination){
            setCurrent_floor(getCurrent_floor() + 1);
            careful = true;
        }
        else{
            setCurrent_floor(getCurrent_floor() - 1);
        }
    }
}
