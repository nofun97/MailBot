package automail;

import java.util.Stack;

public class NewStorageTube {

    // TODO max capacity, should it be in tube or the robot?
    private Stack<MailItem> tube;
    private final int MAXIMUM_CAPACITY;

    public NewStorageTube(int capacity) {
        this.tube = new Stack<>();
        MAXIMUM_CAPACITY = capacity;
    }

    public void addItem(MailItem item){
        if (tube.size() < MAXIMUM_CAPACITY){
            tube.push(item);
        }
    }
    public MailItem peek() {
        return tube.peek();
    }
}
