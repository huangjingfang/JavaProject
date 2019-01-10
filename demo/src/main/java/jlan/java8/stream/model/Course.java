package jlan.java8.stream.model;

public class Course {
	private int courseID;
	private String studentName;
	private int grade;
	public int getCourseID() {
		return courseID;
	}
	public void setCourseID(int courseID) {
		this.courseID = courseID;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return studentName+"\t"+courseID+"\t"+grade;
	}
}
