///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  RealTimeScheduler.java 
// File:             Compare.java
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

import java.util.Comparator;

/**
 * This class implement the Comparator interface with Task as an comparable 
 * object.
 * 
 * @author Minh Bui
 *
 */

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
