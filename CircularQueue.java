import java.util.ArrayList;

/**
 * This class implements Circular Queue data structure. This class is
 * implemented using an ArrayList.
 * 
 * @author Minh Bui
 */
public class CircularQueue<E> implements QueueADT {
	private ArrayList<E> array;
	private int front;
	private int rear;
	private int maxCapacity;
	private int numItems;

	public CircularQueue(int maxCapacity) {
		array = new ArrayList<E>();
		front = -1;
		rear = 0;
		numItems = 0;
		this.maxCapacity = maxCapacity;
	}

	/**
	 * Checks if the queue is empty.
	 * 
	 * @return true if the queue is empty; otherwise false
	 */
	public boolean isEmpty() {
		return numItems == 0;
	}

	/**
	 * Checks if the queue is full.
	 * 
	 * @return true if the queue is full; otherwise false
	 */
	public boolean isFull() {
		return numItems == maxCapacity;
	}

	/**
	 * Returns the front item of the queue without removing it.
	 * 
	 * @return the front item of the queue
	 * @throws EmptyQueueException
	 */
	public E peek() throws EmptyQueueException {
		if (this.isEmpty())
			throw new EmptyQueueException();
		else
			return array.get(front);
	}

	/**
	 * Removes and returns the front item of the queue.
	 * 
	 * @return the first item in the queue
	 * @throws EmptyQueueException
	 *             if the queue is empty
	 */
	public E dequeue() throws EmptyQueueException {
		if (this.isEmpty())
			throw new EmptyQueueException();
		else {
			E temp = array.get(front);
			if (front < array.size() - 1)
				front++;
			else {
				front = 0;
			}
			if (front == rear) {
				front = -1;
				rear = 0;
			}
			numItems--;
			return temp;
		}
	}

	/**
	 * Inserts the item at the rear of the queue.
	 * 
	 * @param item
	 *            The item to add to the queue.
	 * @throws FullQueueException
	 *             if the queue is full
	 */
	public void enqueue(E item) throws FullQueueException {
		if (this.isFull())
			throw new FullQueueException();
		else {
			
			if (array.size() == maxCapacity) {
				if (rear > front) {
					array.set(0, item);
					rear = 0;
				} else if (rear < front) {
					array.set(rear + 1, item);
					rear++;
				}
			} else {
				array.add(item);
				rear++;
			}
		}
		numItems++;
	}

	/**
	 * Returns the number of items the queue can hold
	 * 
	 * @return the number of items the queue can hold
	 */
	public int capacity() {
		return this.maxCapacity;
	}

	/**
	 * Returns the number of items in the queue
	 * 
	 * @return the number of items in the queue
	 */
	public int size() {
		return numItems;
	}

	/**
	 * Returns a string representation of the queue (for printing).
	 * 
	 * @return a string representation of the queue
	 */
	public String toString() {
		return "";
	}
}
