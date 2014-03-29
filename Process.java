/**
 * This class represents a period process which needs to be scheduled. Each
 * Process has a period p and a compute time c. A new Task for a Process should
 * be generated every p steps in the simulation and needs c total compute
 * resources to complete.
 * 
 * @author TA CS367
 * 
 */

public class Process {
	protected int period;
	protected int compute_time;

	public Process(int p, int ct) {
		period = p;
		compute_time = ct;
	}

	public int getPeriod() {
		return period;
	}

	public int getComputeTime() {
		return compute_time;
	}
}
