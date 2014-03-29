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

			CircularQueue<ComputeResource> compRes = new CircularQueue<ComputeResource>(
					capCirQ);
			PriorityQueue<Task> prioTasks = new PriorityQueue<Task>(
					new Compare(), capPrioQ);
			ComputeResourceGenerator compResGen = new ComputeResourceGenerator(
					maxCompRes);
			ProcessGenerator proGen = new ProcessGenerator();

			ArrayList<ComputeResource> availableResource = compResGen
					.getResources();
			ArrayList<Integer> listOfPeriods = new ArrayList<Integer>();

			for (int i = 0; i < availableResource.size(); i++)
				compRes.enqueue(availableResource.get(i));

			while (in.hasNext()) {
				String[] processParam = in.nextLine().split(" ");
				proGen.addProcess(Integer.parseInt(processParam[0]),
						Integer.parseInt(processParam[1]));
				listOfPeriods.add(Integer.parseInt(processParam[0]));
			}

			int T = lcmList(listOfPeriods);
			for (int i = 0; i < T; i++)	{
				ArrayList<Task> tasks = proGen.getTasks(i);
				
			}

		} catch (FileNotFoundException e) {
			System.err.println("File not found!");
			return;
		} catch (Exception e) {
			System.err.println("Something went wrong.");
			return;
		}

		in.close();
	}

	/**
	 * Compute the least common multiple of an array list of integers
	 * 
	 * @param list
	 *            An array list of integers.
	 * @return The largest common multiple of those integers.
	 */
	private static int lcmList(ArrayList<Integer> list) {
		int result = 0;
		if (list.size() == 2)
			return lcm(list.get(0), list.get(1));
		else {
			for (int i = list.size() - 1; i >= 2; i--) {
				result = lcm(list.get(i), lcm(list.get(i - 1), list.get(i - 2)));
			}
			return result;
		}
	}

	/**
	 * Compute the least common multiple of 2 integers.
	 * @param a The first integer.
	 * @param b The second integer.
	 * @return The largest common multiple of a and b.
	 */
	private static int lcm(int a, int b) {
		return (a * b / gcd(a, b));
	}

	/**
	 * Compute the greatest common divisor of 2 integers.
	 * @param a The first integer.
	 * @param b The second integer.
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
