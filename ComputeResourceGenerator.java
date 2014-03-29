import java.util.ArrayList;
import java.util.Random;

/**
 * This class is used to track ComputeResources and generate some at every
 * timestep
 * 
 * @author TA CS367.
 * 
 */
public class ComputeResourceGenerator {
	private int maxCreate;
	private Random rng;

	/**
	 * This creates a ComputeResourceGenerator that will make at most max
	 * compute resources available on each call to getResources().
	 * 
	 * @param max
	 */
	public ComputeResourceGenerator(int max) {
		maxCreate = max;
		rng = new Random();
	}

	/**
	 * This method gets the compute resources which are made available when the
	 * method is called.
	 * 
	 * @return res An array list of the compute resources
	 */
	public ArrayList<ComputeResource> getResources() {
		ArrayList<ComputeResource> res = new ArrayList<ComputeResource>();
		int num = rng.nextInt() % maxCreate;
		for (int i = 0; i < num + 1; i++)
			res.add(new ComputeResource(1));
		return res;
	}
}
