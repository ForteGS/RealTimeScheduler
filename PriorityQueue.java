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
	// For comparing objects.
	private Comparator<E> comp;

	/**
	 * Constructor. Initialize the array list.
	 * 
	 * @param comparator
	 * @param maxCapacity
	 */
	public PriorityQueue(Comparator<E> comp, int maxCapacity) {
		array = new ArrayList<E>();
		// Set the first index of the array to null since that vacant slot won't
		// be used.
		array.add(null);
		this.maxCapacity = maxCapacity;
		numItems = 0;
		this.comp = comp;
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
		E lastItem = array.remove(array.size() - 1);
		numItems--;

		// Reheapify the heap.
		if (array.size() - 1 > 1) {
			array.set(1, lastItem);
			reheapify();
		}
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
		array.add(null);
		int index = array.size() - 1;

		// Reheapify.
		while (index > 1 && comp.compare(getParent(index), item) > 0) {
			array.set(index, getParent(index));
			index = getParentIndex(index);
		}

		// Store the new element.
		array.set(index, item);
		numItems++;
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
			stringOutput += array.get(i + 1).toString() + "\n";
		}
		stringOutput += "\nSize: " + array.size();
		return stringOutput;
	}

	/**
	 * Reheapify the array tree so that the array has the properties of a
	 * min-heap tree. This method reheapifies the tree so that the smallest
	 * element will be the root of the tree.
	 */
	private void reheapify() {
		E root = array.get(1);

		int lastIndex = array.size() - 1;

		int index = 1;
		boolean more = true;
		while (more) {
			int childIndex = getLeftChildIndex(index);
			if (childIndex <= lastIndex) {
				E child = getLeftChild(index);

				// Use right child instead if it is smaller.
				if (getRightChildIndex(index) <= lastIndex
						&& comp.compare(getRightChild(index), child) < 0) {
					childIndex = getRightChildIndex(index);
					child = getRightChild(index);
				}

				// Check if larger child is smaller than root.
				if (comp.compare(child, root) < 0) {
					array.set(index, child);
					index = childIndex;
				} else
					more = false;
			} else
				more = false;
		}

		// Store root element.
		array.set(index, root);
	}

	/**
	 * Returns the index of the left child.
	 * 
	 * @param index
	 *            The index of a node in this queue.
	 * @return The index of the left child of the given node.
	 */
	private int getLeftChildIndex(int index) {
		return 2 * index;
	}

	/**
	 * Returns the index of the right child.
	 * 
	 * @param index
	 *            The index of a node in this queue.
	 * @return the index of the right child of the given node.
	 */
	private int getRightChildIndex(int index) {
		return 2 * index + 1;
	}

	/**
	 * Returns the index of the parent.
	 * 
	 * @param index
	 *            The index of a node in this queue.
	 * @return the index of the parent of the given node.
	 */
	private int getParentIndex(int index) {
		return index / 2;
	}

	/**
	 * Returns the value of the left child.
	 * 
	 * @param index
	 *            the index of a node in this queue.
	 * @return the value of the left child of the given node.
	 */
	private E getLeftChild(int index) {
		return array.get(2 * index);
	}

	/**
	 * Returns the value of the right child.
	 * 
	 * @param index
	 *            the index of a node in this queue.
	 * @return the value of the right child of the given node.
	 */
	private E getRightChild(int index) {
		return array.get(2 * index + 1);
	}

	/**
	 * Returns the value of the parent.
	 * 
	 * @param index
	 *            the index of a node in this queue.
	 * @return the value of the parent of the given node.
	 */
	private E getParent(int index) {
		return array.get(index / 2);
	}
}
