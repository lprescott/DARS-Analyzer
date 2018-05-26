package us.presport.course;

import java.util.ArrayList;

public class Course {
	private Status status;
	private String grade;
	private int credit;
	private String term;
	private String code;
	private String name;
	private double gradeNum;
	private String department;
	private String codeNum;
	private String semester;
	private int year;
	
	public enum Status{
		COMPLETED("C"),
		INPROGRESS("IP");
		
		private String acronym;
		
		Status(String acronym) {
			this.acronym = acronym;
		}
		
		public String getAcronym() {
			return this.acronym;
		}
	}

	/**
	 * @return the status
	 */
	public Status getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(Status status) {
		this.status = status;
	}

	/**
	 * @return the grade
	 */
	public String getGrade() {
		return grade;
	}

	/**
	 * @param grade the grade to set
	 */
	public void setGrade(String grade) {
		this.grade = grade;

		for(Grade g : Grade.values()){
			if(g.getLetterGrade().equals(this.getGrade())){
				setGradeNum(g.getGPAval());
			}
		}
	}

	/**
	 * @return the term
	 */
	public String getTerm() {
		return term;
	}

	/**
	 * @param term the term to set
	 */
	public void setTerm(String term) {
		this.term = term;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param status
	 * @param grade
	 * @param credit
	 * @param term
	 * @param code
	 * @param name
	 */
	public Course(Status status, String grade, int credit, String term, String code, String name) {
		super();
		this.status = status;
		this.grade = grade;
		this.credit = credit;
		this.term = term;
		this.code = code;
		
		StringBuilder nameBuilder = new StringBuilder();
		for(char c : name.toCharArray()){
			if(c == ','){
				nameBuilder.append(' ');
			} else{
				nameBuilder.append(c);
			}
		}

		this.name = nameBuilder.toString();

		for(Grade g : Grade.values()){
			if(g.getLetterGrade().equals(grade)){
				setGradeNum(g.getGPAval());
			}
		}

		StringBuilder department = new StringBuilder();
		StringBuilder courseNumber = new StringBuilder();

		for(int x = 0; x < code.toCharArray().length; x++){
			if(x < 4){
				department.append(code.toCharArray()[x]);
			} else{
				courseNumber.append(code.toCharArray()[x]);
			}
		}

		this.setDepartment(department.toString());
		this.setCodeNum(courseNumber.toString());
	}

		/**
	 * @return the gradeNum
	 */
	public double getGradeNum() {
		return gradeNum;
	}

	/**
	 * @param gradeNum the gradeNum to set
	 */
	public void setGradeNum(double gradeNum) {
		this.gradeNum = gradeNum;
	}

	/**
	 * @return the department
	 */
	public String getDepartment() {
		return department;
	}

	/**
	 * @param department the department to set
	 */
	public void setDepartment(String department) {
		this.department = department;
	}

	/**
	 * @return the codeNum
	 */
	public String getCodeNum() {
		return codeNum;
	}

	/**
	 * @param codeNum the codeNum to set
	 */
	public void setCodeNum(String codeNum) {
		this.codeNum = codeNum;
	}

	/**
	 * @return the credit
	 */
	public int getCredit() {
		return credit;
	}

	/**
	 * @param credit the credit to set
	 */
	public void setCredit(int credit) {
		this.credit = credit;
	}
	
	public double getAvg(ArrayList<Course> input) {
		
		double total = 0;
		int count = input.size();
		for(Course c : input) {
			total += Course.gpaToNumber(c.getGrade());
		}
		return total/count;
	}

	public static double gpaToNumber(String grade) {
		for(Grade g : Grade.values()) {
			if(grade.equals(g.getLetterGrade())) {
				return g.getGPAval();
			}
		}
		return 0;
	}
	
	//A-, B+, B, B-, C+, C, C-, D+, D, D-, E
	public enum Grade{
		A(4.0, "A"),
		AMINUS(3.70, "A-"),
		BPLUS(3.30, "B+"),
		B(3.00, "B"),
		BMINUS(2.70, "B-"),
		CPLUS(2.30, "C+"),
		C(2.00, "C"),
		CMINUS(1.70, "C-"),
		DPLUS(1.30, "C"),
		D(1.00, "D"),
		DMINUS(0.70, "D-"),
		E(0.00, "E");
		
		private double GPAval;
		private String letterGrade;
		
		Grade(double GPAval, String letterGrade){
			this.GPAval = GPAval;
			this.letterGrade = letterGrade;
		}

		/**
		 * @return the gPAval
		 */
		public double getGPAval() {
			return GPAval;
		}

		/**
		 * @param gPAval the gPAval to set
		 */
		public void setGPAval(double gPAval) {
			GPAval = gPAval;
		}

		/**
		 * @return the letterGrade
		 */
		public String getLetterGrade() {
			return letterGrade;
		}

		/**
		 * @param letterGrade the letterGrade to set
		 */
		public void setLetterGrade(String letterGrade) {
			this.letterGrade = letterGrade;
		}
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String format = String.format("%s,%s,%s,%s,%s,%s,%s", this.getGrade(), this.getGradeNum(), this.getCredit(), this.getTerm(), this.getDepartment(), this.getCodeNum(), this.getName());
		return format;
	}
}
