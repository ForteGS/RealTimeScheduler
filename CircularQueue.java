import java.util.ArrayList;

/**
 * This class implements Circular Queue data structure.
 * @author Minh Bui
 */
public class CircularQueue<E> implements QueueADT {
	private ArrayList<E> array;
	private int front;
	private int rear;
	
	
	public CircularQueue()	{
		array = new ArrayList<E>();
		front = -1;
		rear = 0;
	}
	
	/**
	 * Checks if the queue is empty.
	 * @return true if the queue is empty; otherwise false
	 */
	public boolean isEmpty()	{
		return front == -1;
	}
	
	/**
	 * Checks if the queue is full.
	 * @return true if the queue is full; otherwise false
	 */
	public boolean isFull()	{
		return front == rear;
	}
	
	/**
	 * Returns the front item of the queue without removing it.
	 * @return the front item of the queue
	 * @throws EmptyQueueException
	 */
	public E peek() throws EmptyQueueException	{
		if (this.isEmpty()) throw new EmptyQueueException();
		else	
			return array.get(front);
	}
	
	/**
	 * Removes and returns the front item of the queue.
	 * 
	 * @return the first item in the queue
	 * @throws EmptyQueueException if the queue is empty
	 */
	public E dequeue() throws EmptyQueueException	{
		
	}
	
	/**
	 * Inserts the item at the rear of the queue.
	 * 
	 * @param item The item to add to the queue.
	 * @throws FullQueueException if the queue is full
	 */
	public void enqueue(E item) throws FullQueueException	{
		
	}
	
	/**
	 * Returns the number of items the queue can hold
	 * 
	 * @return the number of items the queue can hold
	 */
	public int capacity()	{
		return 0;
	}
	
	/**
	 * Returns the number of items in the queue
	 * 
	 * @return the number of items in the queue
	 */
	public int size()	{
		return 0;
	}
	
	/**
	 * Returns a string representation of the queue (for printing).
	 * 
	 * @return a string representation of the queue
	 */
	public String toString()	{
		
	}
}
