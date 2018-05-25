package us.presport.student;

public class Student {
	private String name;
	private double GPA;
	private String major;
	private String degreeType;
	private int transferCredits;
	private int earnedCredits;
	private int activeCredits;
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * 
	 */
	public Student() {
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the gPA
	 */
	public double getGPA() {
		return GPA;
	}
	/**
	 * @param gPA the gPA to set
	 */
	public void setGPA(double gPA) {
		GPA = gPA;
	}
	/**
	 * @return the transferCredits
	 */
	public int getTransferCredits() {
		return transferCredits;
	}
	/**
	 * @param transferCredits the transferCredits to set
	 */
	public void setTransferCredits(int transferCredits) {
		this.transferCredits = transferCredits;
	}
	/**
	 * @return the earnedCredits
	 */
	public int getEarnedCredits() {
		return earnedCredits;
	}
	/**
	 * @param earnedCredits the earnedCredits to set
	 */
	public void setEarnedCredits(int earnedCredits) {
		this.earnedCredits = earnedCredits;
	}
	/**
	 * @return the activeCredits
	 */
	public int getActiveCredits() {
		return activeCredits;
	}
	/**
	 * @param activeCredits the activeCredits to set
	 */
	public void setActiveCredits(int activeCredits) {
		this.activeCredits = activeCredits;
	}
	/**
	 * @return the major
	 */
	public String getMajor() {
		return major;
	}
	/**
	 * @param major the major to set
	 */
	public void setMajor(String major) {
		this.major = major;
	}
	/**
	 * @return the degreeType
	 */
	public String getDegreeType() {
		return degreeType;
	}
	/**
	 * @param degreeType the degreeType to set
	 */
	public void setDegreeType(String degreeType) {
		this.degreeType = degreeType;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Student [name=" + name + ", GPA=" + GPA + ", major=" + major + ", degreeType=" + degreeType
				+ ", transferCredits=" + transferCredits + ", earnedCredits=" + earnedCredits + ", activeCredits="
				+ activeCredits + "]";
	}
}
