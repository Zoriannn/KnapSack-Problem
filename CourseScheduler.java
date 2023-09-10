package group9;

import java.util.*;

public abstract class CourseScheduler implements CourseSelectionAlgorithm {
	
	/*
	 * @param courseList 		List of courses to choose from
	 * @param maxCreditHours 	Maximum allowable total credit hours
	 * @return					List of selected courses that maximizes rate within the credit hour limit
	 */
	
	
	// Greedy algorithm selects courses based on the highest rate while following the maximum credit hour constraint
	public static List<ReadCourse> greedyCourseSchedule(List<ReadCourse> courseList, int maxCreditHours) {
		// Sort the course list in ascending order of rates
		courseList.sort((a, b) -> Double.compare(Double.parseDouble(a.getRate()), Double.parseDouble(b.getRate())));

		List<ReadCourse> selectedCourses = new ArrayList<>();
		int totalCreditHours = 0;

		//add the sorted courses into the selected courses list without exceeding the max credit hour
		for (ReadCourse course : courseList) {
			if (Integer.parseInt(course.getCourseCreditHour()) <= maxCreditHours - totalCreditHours) {
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

	
	// Dynamic programming algorithm selects courses while following the maximum credit hour constraint
	public static List<ReadCourse> dynamicProgrammingCourseSchedule(List<ReadCourse> courseList, int maxCreditHours) {
		int numCourses = courseList.size();

        // Initialize a 2D array to store the maximized importance
        int[][] maxImportanceMatrix = new int[numCourses + 1][maxCreditHours + 1];

        // Create arrays to store course weights (credit hour) and values (rate)
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
        
        // Start the backtrack loop to reconstruct the selected courses
        while (i > 0 && j > 0) {
            
        	// If the current cell's value is different from the cell above it, or it's the first iteration for this row
        	if (maxImportanceMatrix[i][j] != maxImportanceMatrix[i - 1][j] || i == numCourses) {
                ReadCourse selectedCourse = courseList.get(i - 1); // Retrieve the course list at this position
                selectedCourses.add(selectedCourse); // Add the selected course to the list
                j -= weights[i - 1]; // Reduce available credit hours by the credit hours of the selected course
            }
            i--; // Move to the previous row (course)
        }
        
        Collections.reverse(selectedCourses); // Reverse the list to maintain the original order
        
        return selectedCourses;
    }
}
