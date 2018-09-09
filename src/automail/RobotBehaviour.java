package automail;

import exceptions.ExcessiveDeliveryException;

public interface RobotBehaviour {
    // TODO should this be an interface or an abstract class?
    // This is trying to use the adaptor pattern
    void dispatch();
    void step() throws ExcessiveDeliveryException;
    void setRoute();
    void moveTowards(int destination);

}
