package automail;

public interface RobotBehaviour {
    // TODO should this be an interface or an abstract class?
    // This is trying to use the adaptor pattern
    StorageTube tube = new StorageTube();
    void dispatch();
    void step();
}
