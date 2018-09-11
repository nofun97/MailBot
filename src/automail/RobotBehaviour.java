package automail;

import exceptions.ExcessiveDeliveryException;
import exceptions.FragileItemBrokenException;
import exceptions.ItemTooHeavyException;
import exceptions.NoValidRobotsAvailableException;

public interface RobotBehaviour {
    void dispatch();
    void step() throws ExcessiveDeliveryException, ItemTooHeavyException, FragileItemBrokenException, NoValidRobotsAvailableException;
    void setRoute() throws ItemTooHeavyException;
    void moveTowards(int destination) throws FragileItemBrokenException;

}
