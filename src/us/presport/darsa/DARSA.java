package us.presport.darsa;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.util.ArrayList;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;

import com.opencsv.CSVWriter;

import us.presport.course.Course;
import us.presport.course.Course.Status;
import us.presport.student.Student;

public class DARSA {

    public static void main(String[] args)  {
		
		//Create the user object, a user has a list of courses
		Student user = new Student();

		//Create a list of courses
	    ArrayList<Course> courses = new ArrayList<Course>();
    	
    	if(args.length == 1) {
    		if(args[0].equals("help") || args[0].equals("h")) {
    			System.out.println("DARSA takes arguments in the form of: "
    					+ "String String\n"
    					+ "Where the first String is the relative or absolute "
    					+ "path to the Degree Audit Report in pdf format and "
    					+ "the second String is the relative or absolute path "
    					+ "for the outputted csv that is to be created.");
    		}
    	} else if (args.length == 2) {
    		File file = new File(args[0]);
    		if(!file.isFile() || file.isDirectory()) { 
        		System.out.println("ERROR: Invalid Degree Audit Report pdf "
        				+ "supplied.");	
    		}
	
    		try {
				PDDocument document = PDDocument.load(file);
				PDFTextStripper pdfStripper = new PDFTextStripper();
				String text = pdfStripper.getText(document);
			    
			    BufferedReader bufReader = new BufferedReader(new StringReader(text));
			    String line; int lineNumber = 1; int startOfSummary = 99999;
			    			    
				while( (line=bufReader.readLine()) != null )
			    {
				    lineNumber++;
				    
				    if(lineNumber == 8) {
				    	user.setMajor(line.trim());
				    } else if(lineNumber == 9) {
				    	user.setDegreeType(line.trim());
				    } else if(lineNumber == 32) {
				    	int count = 0;
				    	for (String word : line.split("\\s+")) {
				    		if(count == 2) {
				    			user.setTransferCredits(Integer.parseInt(word));
				    		} 
				    		count ++;
				    	}
				    } else if(line.contains("====  UALBANY ACADEMIC SUMMARY  ====")) {
				    	startOfSummary = lineNumber-1; 
				    } 
				    
				    if(lineNumber == (startOfSummary+2)) {
				    	int count = 0;
				    	for (String word : line.split("\\s+")) {
				    		if(count == 2) {
				    			user.setEarnedCredits(Integer.parseInt(word));
				    		} else if (count == 4) {
				    			user.setGPA(Double.parseDouble(word));
				    		} 
				    		count ++;
				    	}
				    } else if(lineNumber == (startOfSummary+3)) {
				    	int count = 1;
				    	for (String word : line.split("\\s+")) {
				    		if(count == 3) {
				    			user.setActiveCredits(Integer.parseInt(word));
				    		} 
				    		count ++;
				    	}
				    }
				    				    
				    if((lineNumber > startOfSummary) && (line.trim().startsWith("SP") || line.trim().startsWith("FA")) && (!line.trim().startsWith("SPLIT"))) {
				    	int count = 1; String term = null; String code = null; int credit = 0; String grade = null; String courseName = null;
				    	StringBuilder courseNameBuilder = new StringBuilder();
				    	for (String word : line.trim().split("\\s+")) {				    		
				    		if(count == 1) {
				    			term = word;
				    		} else if(count == 2) {
				    			code = word;
				    		} else if(count == 3) {
				    			credit = (int) Double.parseDouble(word);
				    		} else if(count == 4) {
				    			grade = word;
				    		} else if(count > 4 && !grade.equals("IP")) {
				    			courseNameBuilder.append(word + " ");
				    			courseName = courseNameBuilder.toString();
				    		} else if(count > 5) {
				    			courseNameBuilder.append(word + " ");
				    			courseName = courseNameBuilder.toString();
				    		}
				    		count ++;
				    	}
				    	
				    	Status status;
			    		if(grade.equals("IP")) {
			    			 status = Status.INPROGRESS;
			    		} else {
			    			 status = Status.COMPLETED;
			    		}
			    		
						courses.add(new Course(status, grade, credit, term, code, courseName));
				    }
				    
			    }
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.getLocalizedMessage();
			} 
    		
    		double cumulativeGPA = findGPA(courses);
    		double departmentalGPA = findGPADepartmental(courses);
    		
    		DecimalFormat numberFormat = new DecimalFormat("#.000");
    		cumulativeGPA = Double.parseDouble(numberFormat.format(cumulativeGPA));
    		departmentalGPA = Double.parseDouble(numberFormat.format(departmentalGPA));
			try { 
				FileOutputStream fos = new FileOutputStream(args[1]);
				OutputStreamWriter osw = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
				CSVWriter writer = new CSVWriter(osw);
				for(Course c: courses){
					writer.writeNext(toStringArray(c));
				}

				writer.close();	
			} catch(Exception e){
				e.getLocalizedMessage();
			}
    		
    	} else {
    		System.out.println("ERROR: Invalid command "
    				+ "line arguments detected.");	
    	} 
	}
	
	private static String [] toStringArray(Course course){
		ArrayList<String> courseDetails = new ArrayList<String>();
		for (String word : course.toString().split(",")) {
			courseDetails.add(word);
		}
		String [] detailsAsArray = new String[courseDetails.size()];
		detailsAsArray = courseDetails.toArray(detailsAsArray);
		return detailsAsArray;
	}

	private static double findGPADepartmental(ArrayList<Course> courses) {
		double total = 0;
		int count = 0;

		for(Course c : courses) {
			
			if(!c.getGrade().equals("IP") && c.getCode().contains("ICSI")) {
				total+= Course.gpaToNumber(c.getGrade());
				count++;
			}
		}
		return total/count;
	}

	private static double findGPA(ArrayList<Course> courses) {
		double total = 0;
		int count = 0;

		for(Course c : courses) {
			
			if(!c.getGrade().equals("IP")) {
				total+= Course.gpaToNumber(c.getGrade());
				count++;
			}
		}
		return total/count;
	}
}
