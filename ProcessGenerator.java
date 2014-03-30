///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  RealTimeScheduler.java 
// File:             ProcessGenerator.java
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
