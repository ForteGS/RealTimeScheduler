import java.util.Comparator;

public class Compare implements Comparator<Task> {

	@Override
	public int compare(Task e1, Task e2) {
		if (e1.getDeadline() < e2.getDeadline())
			return -1;
		if (e1.getDeadline() > e2.getDeadline())
			return 1;
		else
			return 0;
	}
	
}
