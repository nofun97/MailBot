package automail;

import exceptions.ExcessiveDeliveryException;
import exceptions.FragileItemBrokenException;
import exceptions.ItemTooHeavyException;

public interface RobotBehaviour {
    // TODO should this be an interface or an abstract class?
    // This is trying to use the adaptor pattern
    void dispatch();

    void step() throws ExcessiveDeliveryException, ItemTooHeavyException,
            FragileItemBrokenException;
}
