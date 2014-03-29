import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class RealTimeScheduler {
	public static void main(String[] args) {
		ProcessGenerator pg = new ProcessGenerator();
		testPG(pg);
		/*
		if (args.length != 1) {
			System.err.println("Invalid arguments. The first and only command "
					+ "line argument will be the name of the "
					+ "configuration file.");
			return;
		}

		File fileIn = new File(args[0]);
		Scanner in;

		try {
			in = new Scanner(fileIn);
		} catch (FileNotFoundException e) {
			System.err.println("File not found!");
			return;
		}

		in.close();
		*/
	}
	
	private static void testPG(ProcessGenerator pg)	{
		pg.addProcess(5, 3);
		pg.addProcess(10, 2);
		pg.addProcess(11, 1);
		ArrayList<Task> tasks = pg.getTasks(10);
		for (int i = 0; i < tasks.size(); i++)	{
			System.out.println(tasks.get(i));
		}
	}
}
