<<<<<<< HEAD
import java.util.ArrayList;
import java.util.Comparator;

/**
 * This class implements priority Queue data structure using array-based
 * min-heap.
 * 
 * @author Minh Bui
 */
=======
public class PriorityQueue {
    private int capacity;
    
    /**
     * Constructor
     */
    public PriorityQueue(Comparator<E> comparator, int maxCapacity) {
      
    }
    
    /**
	  * Checks if the queue is empty.
	  * @return true if the queue is empty; otherwise false
	  */
    public boolean isEmpty()  {
      return true;
    }
    
    /**
	  * Checks if the queue is full.
	  * @return true if the queue is full; otherwise false
	  */
    public boolean isFull() {
      
    }
	
	  /**
	  * Returns the front item of the queue without removing it.
	  * @return the front item of the queue
	  * @throws EmptyQueueException
	  */
	  public E peek() throws EmptyQueueException  {
	    
	  }
	
	  /**
	  * Removes and returns the front item of the queue.
	  * 
	  * @return the first item in the queue
	  * @throws EmptyQueueException if the queue is empty
	   */
	  public E dequeue() throws EmptyQueueException {
	    
	  }
	
	  /**
	  * Inserts the item at the rear of the queue.
	  * 
	  * @param item The item to add to the queue.
	  * @throws FullQueueException if the queue is full
	  */
	  public void enqueue(E item) throws FullQueueException {
	    
	  }
	
	  /**
	  * Returns the number of items the queue can hold
	  * 
	  * @return the number of items the queue can hold
	  */
	  public int capacity() {
	    
	  }
	
	  /**
	  * Returns the number of items in the queue
	  * 
	  * @return the number of items in the queue
	  */
	  public int size() {
	    
	  }
	
	  /**
	  * Returns a string representation of the queue (for printing).
	  * 
	  * @return a string representation of the queue
	  */
	  public String toString()  {
	    return "";
	  }
>>>>>>> 217e4ba54dc9582d1171cf78e61ba12af49d3a5d

public class PriorityQueue<E> implements QueueADT<E> {
	private ArrayList<E> array;
	private int maxCapacity;
	private int numItems;
	private Comparator<E> comp;

	/**
	 * Constructor.
	 * 
	 * @param comparator
	 * @param maxCapacity
	 */
	public PriorityQueue(Comparator<E> comparator, int maxCapacity) {
		array = new ArrayList<E>();
		array.add(null);
		this.maxCapacity = maxCapacity;
		numItems = 0;
		comp = comparator;
	}

	@Override
	/**
	 * Checks if the queue is empty.
	 * @return true if the queue is empty; otherwise false
	 */
	public boolean isEmpty() {
		return numItems == 0;
	}

	@Override
	/**
	 * Checks if the queue is full.
	 * @return true if the queue is full; otherwise false
	 */
	public boolean isFull() {
		return numItems == maxCapacity;
	}

	@Override
	/**
	 * Returns the front item of the queue without removing it.
	 * @return the front item of the queue
	 * @throws EmptyQueueException
	 */
	public E peek() throws EmptyQueueException {
		if (this.isEmpty())
			throw new EmptyQueueException();
		return array.get(1);
	}

	@Override
	/**
	 * Removes and returns the front item of the queue.
	 * 
	 * @return the first item in the queue
	 * @throws EmptyQueueException if the queue is empty
	 */
	public E dequeue() throws EmptyQueueException {
		// TODO Auto-generated method stub
		E temp = array.get(1);
		array.remove(1);
		numItems--;
		reheapify();
		return temp;
	}

	@Override
	/**
	 * Inserts the item at the rear of the queue.
	 * 
	 * @param item The item to add to the queue.
	 * @throws FullQueueException if the queue is full
	 */
	public void enqueue(E item) throws FullQueueException {
		// TODO Auto-generated method stub
		if (this.isFull())
			throw new FullQueueException();
		array.add(item);
		numItems++;
		reheapify();
	}

	@Override
	/**
	 * Returns the number of items the queue can hold
	 * 
	 * @return the number of items the queue can hold
	 */
	public int capacity() {
		return maxCapacity;
	}

	@Override
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
		String stringOutput = "";
		for(int i = 0; i < numItems; i++)	{
			stringOutput += array.get(i).toString();
		}
		return stringOutput;
	}

	/**
	 * Reheapify the array tree so that the array has the properties of a tree.
	 * This method reheapifies the tree so that the smallest element will be 
	 * the root of the tree.
	 */
	private void reheapify() {
		int child = numItems;
		boolean done = false;
		while (!done) {
			int parent = child / 2;
			if (parent == 0)
				done = true;
			else if (comp.compare(array.get(child), array.get(parent)) <= 0)
				done = true;
			else {
				E temp = array.get(child);
				array.set(child, array.get(parent));
				array.set(parent, temp);
				child = parent;
			}
		}
	}
}
