package strategies;

import java.util.LinkedList;
import java.util.Comparator;
import java.util.ListIterator;
import java.util.function.Consumer;

import automail.*;
import exceptions.NoValidRobotsAvailableException;
import exceptions.TubeFullException;
import exceptions.FragileItemBrokenException;

/**
 * The type My mail pool.
 */
public class MyMailPool implements IMailPool {
	private class Item {
		/**
		 * The Priority.
		 */
		int priority;
		/**
		 * The Destination.
		 */
		int destination;
		/**
		 * The Heavy.
		 */
		boolean heavy;
		/**
		 * The Mail item.
		 */
		MailItem mailItem;
		// Use stable sort to keep arrival time relative positions

		/**
		 * Instantiates a new Item.
		 *
		 * @param mailItem the mail item
		 */
		public Item(MailItem mailItem) {
			priority = (mailItem instanceof PriorityMailItem) ? ((PriorityMailItem) mailItem).getPriorityLevel() : 1;
			heavy = mailItem.getWeight() >= 2000;
			destination = mailItem.getDestFloor();
			this.mailItem = mailItem;
		}
	}

	private boolean carefulRobotExists = false;
	private boolean strongRobotExists = false;

	/**
	 * The type Item comparator.
	 */
	public class ItemComparator implements Comparator<Item> {
		@Override
		public int compare(Item i1, Item i2) {
			int order = 0;
			if (i1.priority < i2.priority) {
				order = 1;
			} else if (i1.priority > i2.priority) {
				order = -1;
			} else if (i1.destination < i2.destination) {
				order = 1;
			} else if (i1.destination > i2.destination) {
				order = -1;
			}
			return order;
		}
	}
	
	private LinkedList<Item> pool;
	private LinkedList<Item> fragilePool;
	private LinkedList<Robot> robots;
	private int lightCount;

	/**
	 * Instantiates a new My mail pool.
	 */
	public MyMailPool(){
		// Start empty
		pool = new LinkedList<Item>();
		lightCount = 0;
		robots = new LinkedList<Robot>();
		fragilePool = new LinkedList<>();
	}

	public void addToPool(MailItem mailItem) throws NoValidRobotsAvailableException {
		Item item = new Item(mailItem);
        if (mailItem.getFragile()){
        	// checking if there is careful robot to deliver fragile items
        	if (!carefulRobotExists) throw new NoValidRobotsAvailableException("Careful");
            fragilePool.add(item);
            fragilePool.sort(new ItemComparator());
            return;
        }
        pool.add(item);
        if (!item.heavy) {
        	lightCount++;
		} else if (item.heavy && !strongRobotExists){
        	// checking if there is strong robot to deliver heavy items
        	throw new NoValidRobotsAvailableException("Strong");
		}
        pool.sort(new ItemComparator());
	}
	
	@Override
	public void step() throws FragileItemBrokenException {
		for (Robot robot: (Iterable<Robot>) robots::iterator) { fillStorageTube(robot); }
	}
	
	private void fillStorageTube(Robot robot) throws FragileItemBrokenException {
		StorageTube tube = robot.getTube();
		StorageTube temp = new StorageTube(tube);

		try { // Get as many items as available or as fit

			if (robot instanceof CarefulRobot){
				while(temp.getSize() < ((CarefulRobot) robot).MAX_FRAGILE_ITEMS
						&& !fragilePool.isEmpty() ) {
					Item item = fragilePool.remove();
					temp.addItem(item.mailItem);
				}
			}

			if (robot.isStrong()) {
				while(temp.getSize() < robot.getMaxItems() && !pool.isEmpty() ) {
					Item item = pool.remove();
					if (!item.heavy) lightCount--;
					temp.addItem(item.mailItem);
				}
			} else {
				ListIterator<Item> i = pool.listIterator();
				while(temp.getSize() < robot.getMaxItems() && lightCount > 0) {
					Item item = i.next();
					if (!item.heavy) {
						temp.addItem(item.mailItem);
						i.remove();
						lightCount--;
					}
				}
			}
			if (temp.getSize() > 0) {
				while (!temp.isEmpty()) tube.addItem(temp.pop());
				robot.dispatch();
			}

		}
		catch(TubeFullException e){
			e.printStackTrace();
		}
	}

	@Override
	public void registerWaiting(Robot robot) { // assumes won't be there

		// checking if necessary robot types exists in the lineup
		if (robot instanceof CarefulRobot) carefulRobotExists = true;
		if (robot.isStrong()) {
			robots.add(robot);
			strongRobotExists = true;
		} else {
			robots.addLast(robot); // weak robot last as want more efficient delivery with highest priorities
		}
	}

	@Override
	public void deregisterWaiting(Robot robot) {
		robots.remove(robot);
	}

}
