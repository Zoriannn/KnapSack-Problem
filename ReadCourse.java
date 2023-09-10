package group9;

import java.io.*;
import java.util.*;

/*
 * ReadCourse class reads and stores course information from a text file.
 * This class provides methods to read course data, store them in objects, and retrieve course information.
 */

public class ReadCourse {

	private String courseCode;
	private String courseName;
	private String quantity;
	private String courseCreditHour;
	private String rate;
	private static ArrayList<ReadCourse> courseList = new ArrayList <>();
	
	// Constructors
	public ReadCourse() {
		
	}
	
	public ReadCourse(String courseCode, String courseName,String quantity, String courseCreditHour, String rate) {
		this.courseCode = courseCode;
		this.courseName = courseName;
		this.quantity = quantity;
		this.courseCreditHour = courseCreditHour;
		this.rate = rate;
	}
	
	// Accessors
	public String getCourseCode() {
		return courseCode;
	}
	public String getCourseName() {
		return courseName;
	}
	public String getQuantity() {
		return quantity;
	}
	public String getCourseCreditHour() {
		return courseCreditHour;
	}
	public String getRate() {
		return rate;
	}
	public ArrayList<ReadCourse> getList() {
		return courseList;
	}
	

	/*
	 * readCourseList method reads course information from a text file and fills
	 * the ArrayList 'courseList' with ReadCourse objects.
	 * Exception will be thrown if an error occurs while reading data from the file.
	 */
	public void readCourseList() {
		try {
			// Create a new file object
			String filename = "courselist.txt";
			Scanner inFile = new Scanner(new File(filename));
			
			// Read each line of the file and add it to the ArrayList 
			while (inFile.hasNextLine()) {
				String line = inFile.nextLine();
	            String[] fields = line.split("\\|");
	            String courseCode = fields[0];
	            String courseName = fields[1];
	            String quantity = fields[2];
	            String courseCreditHour = fields[3];
	            String rate = fields[4];
	            
	            ReadCourse readCourse = new ReadCourse(courseCode, courseName, quantity, courseCreditHour, rate);
	            courseList.add(readCourse);
			}
			
	        // Close the file
	        inFile.close();
		} catch(IOException e) {
			e.printStackTrace(); // Prints error message
		}
	}
	
	// toString() method returns a string representation of the ReadCourse object
	public String toString() {
	    return String.format("Course Code        : %1s\nCourse Name        : %2s\nCourse Quantity    : 1\nCourse Credit Hour : %1s\nImportance Rate    : %1s\n", courseCode, courseName, courseCreditHour, rate);
	}

}