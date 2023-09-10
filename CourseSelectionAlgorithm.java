package group9;

import java.util.*;

/**
 * The CourseSelectionAlgorithm interface defines methods to select courses from a provided list that
 * maximize academic importance within the given maximum credit hours constraint.
 */

public interface CourseSelectionAlgorithm {
	
	public static List<ReadCourse> greedyCourseSchedule(List<ReadCourse> courseList, int maxCreditHours) {
		return courseList;
		
	}
	
	public static List<ReadCourse> dynamicProgrammingCourseSchedule(List<ReadCourse> courseList, int maxCreditHours) {
		return courseList;
		
	}
}