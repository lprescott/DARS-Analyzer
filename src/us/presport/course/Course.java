package us.presport.course;

import java.util.ArrayList;

public class Course {
	private Status status;
	private String grade;
	private int credit;
	private String term;
	private String code;
	private String name;
	
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
		this.name = name;
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
		return String.format("%-10s %-10s %-10s %-10s %-40s", this.getGrade(), this.getCredit(), this.getTerm(), this.getCode(), this.getName());
	}
}
