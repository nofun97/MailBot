package automail;

import exceptions.ExcessiveDeliveryException;
import exceptions.FragileItemBrokenException;
import exceptions.ItemTooHeavyException;
import exceptions.NoValidRobotsAvailableException;

public interface RobotBehaviour {
    // TODO should this be an interface or an abstract class?
    // This is trying to use the adaptor pattern
    void dispatch();
    void step() throws ExcessiveDeliveryException, ItemTooHeavyException, FragileItemBrokenException, NoValidRobotsAvailableException;
    void setRoute() throws ItemTooHeavyException;
    void moveTowards(int destination) throws FragileItemBrokenException;

}
