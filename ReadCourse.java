package Assignment;

import java.io.*;
import java.util.*;

/*
 * ReadCourse class reads and stores course information from a text file.
 * This class provides methods to read course data, store them in objects, and retrieve course information.
 */

public class ReadCourse {

	private String courseCode;
	private String courseName;
	private String courseCreditHour;
	private String quantity;
	private String weight;
	private String rate;
	private static ArrayList<ReadCourse> courseList = new ArrayList <>();
	
	// Constructors
	public ReadCourse() {
	}
	
	public ReadCourse(String courseCode, String courseName,String courseCreditHour, String quantity, String weight, String rate) {
		this.courseCode = courseCode;
		this.courseName = courseName;
		this.courseCreditHour = courseCreditHour;
		this.quantity = quantity;
		this.weight = weight;
		this.rate = rate;
	}
	
	// Accessors
	public String getCourseCode() {
		return courseCode;
	}
	public String getCourseName() {
		return courseName;
	}
	public String getCourseCreditHour() {
		return courseCreditHour;
	}
	public String getQuantity() {
		return quantity;
	}
	public String getWeight() {
		return weight;
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
	            String lastDigit = courseCode.substring(courseCode.length() - 1); // Get the last digit of the course code
	            String quantity = fields[2];
	            String weight = fields[3];
	            String rate = fields[4];
	            
	            ReadCourse readCourse = new ReadCourse(courseCode, courseName, lastDigit, quantity, weight, rate);
	            courseList.add(readCourse);
			}
			
	        // Close the file
	        inFile.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public String toString()
	{
		return String.format("Course Code     : %1s\nCourse Name     : %2$s\nCourse Quantity	: %3$s\nCourse Weight	: %4$s\nImportance Rate : %5$s\n", courseCode, courseName, quantity, weight, rate);
	}
}



