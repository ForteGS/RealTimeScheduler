///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  RealTimeScheduler.java 
// File:             PriorityQueue.java
// Semester:         CS367 Spring 2014
//
// Author:           Minh Bui
// CS Login:         minh
// Lecturer's Name:  Jim Skrentny
// Lab Section:      null
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ////////////////////
//                   CHECK ASSIGNMENT PAGE TO see IF PAIR-PROGRAMMING IS ALLOWED
//                   If allowed, learn what PAIR-PROGRAMMING IS, 
//                   choose a partner wisely, and complete this section.
//
// Pair Partner:     null
// CS Login:         null
// Lecturer's Name:  null
// Lab Section:      null
//
//                   STUDENTS WHO GET HELP FROM ANYONE OTHER THAN THEIR PARTNER
// Credits:          null
//////////////////////////// 80 columns wide //////////////////////////////////

import java.util.ArrayList;
import java.util.Comparator;

/**
 * This class implements priority Queue data structure using array-based
 * min-heap.
 * 
 * @author Minh Bui
 */

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
		if (this.isEmpty())
			throw new EmptyQueueException();
		E temp = array.get(1);
		array.remove(1);
		numItems--;
		array.get(array.size() - 1);
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
		for (int i = 0; i < numItems; i++) {
			stringOutput += array.get(i + 1).toString();
		}
		stringOutput += "\nSize: " + array.size();
		return stringOutput;
	}

	/**
	 * Reheapify the array tree so that the array has the properties of a tree.
	 * This method reheapifies the tree so that the smallest element will be the
	 * root of the tree.
	 */
	private void reheapify() {
		int child = numItems;
		boolean done = false;
		while (!done) {
			if (child <= 1)
				done = true;
			int parent = child / 2;
			if (parent == 0)
				done = true;
			else if (comp.compare(array.get(child), array.get(parent)) >= 0) {
				child--;
			} else {
				E temp = array.get(child);
				array.set(child, array.get(parent));
				array.set(parent, temp);
				child = parent;
			}
		}
	}
}
