///////////////////////////////////////////////////////////////////////////////
//ALL STUDENTS COMPLETE THESE SECTIONS
//Title:            Real Time Scheduler
//
//Files:            RealTimeScheduler.java
//					CircularQueue.java
//					Compare.java
//					ComputeResource.java
//					ComputeResourceGenerator.java
//					EmptyQueueException.java
//					FullQueueException.java
//					PriorityQueue.java
//					ProcessGenerator.java
//					QueueADT.java
//					RealTimeScheduler.java
//					Task.java
//
//Semester:         CS367 Spring 2014
//
//Author:           Minh Bui
//Email:            mbui2@wisc.edu
//CS Login:         minh
//Lecturer's Name:  Jim Skrentny
//Lab Section:      null
//
////////////////////PAIR PROGRAMMERS COMPLETE THIS SECTION ////////////////////
//CHECK ASSIGNMENT PAGE TO see IF PAIR-PROGRAMMING IS ALLOWED
//If allowed, learn what PAIR-PROGRAMMING IS, 
//choose a partner wisely, and complete this section.
//
//Pair Partner:     null
//Email:            null
//CS Login:         null
//Lecturer's Name:  null
//Lab Section:      null
//
//STUDENTS WHO GET HELP FROM ANYONE OTHER THAN THEIR PARTNER
//Credits:          null
////////////////////////////80 columns wide //////////////////////////////////

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class RealTimeScheduler {
	public static void main(String[] args) {
		// Checking for the number of passed arguments.
		if (args.length != 1) {
			System.err.println("Invalid arguments. The first and only command "
					+ "line argument will be the name of the "
					+ "configuration file.");
			return;
		}

		// Declaring variables
		Scanner in;
		/**
		 * The maximum compute resource
		 */
		int maxCompRes = 0;
		/**
		 * The max capacity of the circular queue.
		 */
		int capCirQ = 0;
		/**
		 * The max capacity of the priority queue.
		 */
		int capPrioQ = 0;
		File fileIn = new File(args[0]);

		try {
			in = new Scanner(fileIn);
			maxCompRes = Integer.parseInt(in.nextLine());
			capCirQ = Integer.parseInt(in.nextLine());
			capPrioQ = Integer.parseInt(in.nextLine());

			// This array list holds the periods. For calculating the least
			// common multiple.
			ArrayList<Integer> listOfPeriods = new ArrayList<Integer>();
			ProcessGenerator proGen = new ProcessGenerator();
			ComputeResourceGenerator compResGen = new ComputeResourceGenerator(
					maxCompRes);

			// Create new processes and store the period of each process into
			// listOfPeriods.
			while (in.hasNext()) {
				String[] processParam = in.nextLine().split(" ");
				proGen.addProcess(Integer.parseInt(processParam[0]),
						Integer.parseInt(processParam[1]));
				listOfPeriods.add(Integer.parseInt(processParam[0]));
			}

			int T = lcmList(listOfPeriods);
			// Priority queue stores new generated tasks.
			PriorityQueue<Task> prioTasks = new PriorityQueue<Task>(
					new Compare(), capPrioQ);
			CircularQueue<ComputeResource> compRes = new CircularQueue<ComputeResource>(
					capCirQ);

			for (int i = 0; i < T; i++) {
				
				if (!prioTasks.isEmpty() && prioTasks.peek().isComplete())
					prioTasks.dequeue();
				 
				// Get new resource
				ArrayList<ComputeResource> availableResource = compResGen
						.getResources();

				// Get tasks for timesteps i
				ArrayList<Task> tasks = proGen.getTasks(i);

				// This array list contains tasks that are removed due to being
				// applied with computing resource. After applying computing
				// resource, we will put the tasks in this array list back into
				// the priority queue.
				ArrayList<Task> deqTasks = new ArrayList<Task>();

				// Adding resource to the queue.

				for (int k = 0; k < capCirQ; k++) {
					if (!compRes.isFull())
						compRes.enqueue(availableResource.get(k));
				}

				// Adding tasks to the priority queue.
				for (int j = 0; j < tasks.size(); j++)
					prioTasks.enqueue(tasks.get(j));

				System.out.println(prioTasks);
				// Applying resource to the highest tasks in the priority queue.
				while (!compRes.isEmpty() && !prioTasks.isEmpty()) {
					int value = compRes.dequeue().getValue();
					// Applying resources to the tasks, remove the applied
					// resources from the
					// queue. Each task in the queue is applied resource
					// only once per timestep.
					Task deqTask = prioTasks.dequeue();
					deqTask.updateProgress(value);

					// Do not add completed tasks back to the queue.
					if (!deqTask.isComplete())
						deqTasks.add(deqTask);
				}
	
				// Re-adding the incomplete tasks to the queue.
				for (int m = 0; m < deqTasks.size(); m++)
					prioTasks.enqueue(deqTasks.get(m));

				
				// Check for missed deadline.
				try {
					if (prioTasks.peek().missedDeadline(i)) {
						System.out.println("Deadline missed at timestep " + i);
						in.close();
						return;
					}
				} catch (EmptyQueueException e) {
					continue;
				}
			}
			System.out
					.println("Scheduling complete after " + T + " timesteps.");
		} catch (FileNotFoundException e) {
			System.err.println("File not found!");
			return;
		} catch (EmptyQueueException e) {
			System.err.println("Some queues are empty.");
			return;
		} catch (FullQueueException e) {
			System.err.println("Some queues are full.");
			return;
		} catch (Exception e) {
			System.err.println("Something went wrong.");
			return;
		}

	}

	/**
	 * Compute the least common multiple of an array list of integers
	 * 
	 * @param list
	 *            An array list of integers.
	 * @return The largest common multiple of those integers.
	 */
	private static int lcmList(ArrayList<Integer> list) {
		if (list.size() == 0)
			throw new NullPointerException();
		if (list.size() == 1)
			return list.get(0);
		if (list.size() == 2)
			return lcm(list.get(0), list.get(1));
		int result = list.get(list.size() - 1);
		for (int i = list.size() - 1; i >= 0; i--)
			result = lcm(list.get(i), result);
		return result;
	}

	/**
	 * Compute the least common multiple of 2 integers.
	 * 
	 * @param a
	 *            The first integer.
	 * @param b
	 *            The second integer.
	 * @return The largest common multiple of a and b.
	 */
	private static int lcm(int a, int b) {
		return (a * b / gcd(a, b));
	}

	/**
	 * Compute the greatest common divisor of 2 integers.
	 * 
	 * @param a
	 *            The first integer.
	 * @param b
	 *            The second integer.
	 * @return The greatest common divisor of a and b.
	 */
	private static int gcd(int a, int b) {
		while (b != 0) {
			int t = b;
			b = a % b;
			a = t;
		}
		return a;
	}

}
