package exceptions;

import automail.Simulation;

public class NoValidRobotsAvailableException extends Throwable{
    public NoValidRobotsAvailableException(String robot) {
        super("There are no " + robot + " robots available to deliver all " +
                "mails");
    }
}
