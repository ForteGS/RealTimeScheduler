import java.util.ArrayList;

/**
 * The ProcessGenerator class is responsible for tracking the Processes which
 * run in the system and creating the Tasks that are generated each cycle
 * 
 * @author Minh Bui
 * 
 */

public class ProcessGenerator {
	private ArrayList<Process> processList;

	public ProcessGenerator() {
		processList = new ArrayList<Process>();
	}

	public void addProcess(int p, int c) {
		Process newProcess = new Process(p, c);
		processList.add(newProcess);
	}

	public ArrayList<Task> getTasks(int t) {
		ArrayList<Task> tasks = new ArrayList<Task>();
		for (int i = 0; i < processList.size(); i++) {
			if (t % processList.get(i).getPeriod() == 0)	{
				Task newTask = new Task(processList.get(i), t);
				tasks.add(newTask);
			}
		}
		return tasks;
	}
}
