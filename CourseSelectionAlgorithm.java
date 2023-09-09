package Assignment;

import java.util.*;

/*
 * CourseSelectionAlgorithm interface selects courses from the provided list that
 * maximize academic importance within the given max credit hours constraint.
 */

public interface CourseSelectionAlgorithm {
	
	public static List<ReadCourse> greedyCourseSchedule(List<ReadCourse> courseList, int maxCreditHours){
		return courseList;
		
	}
	
	public static List<ReadCourse> dynamicProgrammingCourseSchedule(List<ReadCourse> courseList, int maxCreditHours){
		return courseList;
		
	}
}
