package Assignment;

import java.util.*;

public abstract class CourseScheduler implements CourseSelectionAlgorithm {

	public static List<ReadCourse> greedyCourseSchedule(List<ReadCourse> courseList, int maxCreditHours) {
		courseList.sort((a, b) -> Double.compare(Double.parseDouble(a.getRate()), Double.parseDouble(b.getRate())));

		List<ReadCourse> selectedCourses = new ArrayList<>();
		int totalCreditHours = 0;

		for (ReadCourse course : courseList) {
			if (Integer.parseInt(course.getCourseCreditHour()) <= maxCreditHours - totalCreditHours)
			{
				for (int i = 0; i < Integer.parseInt(course.getQuantity()); i++) {
					if (totalCreditHours + Integer.parseInt(course.getCourseCreditHour()) <= maxCreditHours) {
		                selectedCourses.add(course);
		                totalCreditHours += Integer.parseInt(course.getCourseCreditHour());
		            }
	            }
			}
        }
		return selectedCourses;
	}

	public static List<ReadCourse> dynamicProgrammingCourseSchedule(List<ReadCourse> courseList, int maxCreditHours)
	{
		int numCourses = courseList.size();

        // Initialize a 2D array to store the maximized importance
        int[][] maxImportanceMatrix = new int[numCourses + 1][maxCreditHours + 1];

        // Create arrays to store course weights and values
        int[] weights = new int[numCourses];
        int[] values = new int[numCourses];

        for (int i = 0; i < numCourses; i++) {
            ReadCourse course = courseList.get(i);
            weights[i] = Integer.parseInt(course.getCourseCreditHour());
            values[i] = Integer.parseInt(course.getRate());
        }
        
        // Fill maxImportanceMatrix using dynamic programming approach
        for (int i = 0; i <= numCourses; i++) {
            for (int j = 0; j <= maxCreditHours; j++) {
                if (i == 0 || j == 0)
                    maxImportanceMatrix[i][j] = 0;
                else if (weights[i - 1] <= j)
                    maxImportanceMatrix[i][j] = Math.max(values[i - 1] + maxImportanceMatrix[i - 1][j - weights[i - 1]], maxImportanceMatrix[i - 1][j]);
                else
                    maxImportanceMatrix[i][j] = maxImportanceMatrix[i - 1][j];
            }
        }
        
        // Backtrack to find the selected courses
        List<ReadCourse> selectedCourses = new ArrayList<>();
        int i = numCourses;
        int j = maxCreditHours;
        
        while (i > 0 && j > 0) {
            if (maxImportanceMatrix[i][j] != maxImportanceMatrix[i - 1][j]) {
                ReadCourse selectedCourse = courseList.get(i - 1);
                selectedCourses.add(selectedCourse);
                j -= weights[i - 1];
            }
            i--;
        }
        
        Collections.reverse(selectedCourses); // Reverse the list to maintain the original order
        
        return selectedCourses;
    }
		
}




