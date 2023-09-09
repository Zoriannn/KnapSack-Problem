package Assignment;

import java.util.*;

/*
 * TestClass is the main entry point for the course registration program.
 * Provides a user interface to interactively choose between different algorithms
 * for course selection and manage the registration process.
 */

public class TestClass {
	public static final int MAX_CREDIT_HOURS = 20;	// max credit hour is 20
	
	public static void main(String [] args) {
		Scanner scanner = new Scanner(System.in);
		ReadCourse readCourse = new ReadCourse();
		readCourse.readCourseList();
		List<ReadCourse> courseList = readCourse.getList();


		boolean loop = true;
		while(loop) {
			
			
			// Prints header
			System.out.println("			       	Courses");
			System.out.println("---------------------------------------------------------------------------------------------");
			System.out.printf("%1$-16s %2$-51s %3$-10s %4$-8s %5$-4s %n", "Course Code", "Course Name", "Quantity", "Weight", "Rate");
			System.out.println("---------------------------------------------------------------------------------------------");
			// Prints course details
			for (ReadCourse course : courseList) {
	            System.out.printf("%1$-16s %2$-51s %3$-10s %4$-8s %5$-4s\n",course.getCourseCode(), course.getCourseName(), course.getQuantity(), course.getWeight(), course.getRate());            
			}
			System.out.println("---------------------------------------------------------------------------------------------");
			// Prints options for users to choose between algorithms, or exit.
			System.out.println("\nOption:");
			System.out.println("1. Greedy Algorithm\n2. Dynamic Programming\n3. Exit");
			System.out.print("Please select an option (1, 2, 3): ");
			int option = scanner.nextInt();
			
			switch (option) {
			case 1:
				ArrayList<ReadCourse> greedySelectedCourses = (ArrayList<ReadCourse>) CourseScheduler.greedyCourseSchedule(courseList, MAX_CREDIT_HOURS);
                System.out.println("\nSelected Courses using Greedy Algorithm:");
                for (ReadCourse course : greedySelectedCourses) {
                	System.out.println(course.toString());
                }
                System.out.print("Press ENTER key to continue...");
                scanner.nextLine();
                scanner.nextLine();
                break;
			case 2:
				 ArrayList<ReadCourse> dynamicSelectedCourses = (ArrayList<ReadCourse>) CourseScheduler.dynamicProgrammingCourseSchedule(courseList, MAX_CREDIT_HOURS);
                 System.out.println("\nSelected Courses using Dynamic Programming:");
                 for (ReadCourse course : dynamicSelectedCourses) {
                     System.out.println(course.toString());
                 }
                 System.out.print("Press ENTER key to continue...");
                 scanner.nextLine();
                 scanner.nextLine();
                 break;
			case 3:
				loop = false;
				System.out.println("Exit successful.");
				break;
			default:
				// When the user enters something other than 1, 2, or 3
				System.out.println("Invalid option. Please try again.");
			}
			
		}
		scanner.close();
	}
}


