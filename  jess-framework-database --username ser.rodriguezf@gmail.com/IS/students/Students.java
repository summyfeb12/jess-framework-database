package com.is.students;

public class Students implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private long studentId;
	private String studentName;
	private String studentSurname;
	private String studentPhone;
	
	public Students () {
		
	}
	
	public Students (long id, String name, String surname, String phone) {
		
		this.studentId = id;
		this.studentName = name;
		this.studentSurname = surname;
		this.studentPhone = phone;
		
	}
	
	public long getStudentId() {
		return this.studentId;
	}

	public void setStudentId (long studentId) {
		this.studentId = studentId;
	}

	public String getStudentName() {
		return this.studentName;
	}

	public void setStudentName (String studentName) {
		this.studentName = studentName;
	}
	
	public String getStudentSurname() {
		return this.studentSurname;
	}
	
	public void setStudentSurname (String studentSurname) {
		this.studentSurname = studentSurname;
	}
	
	public String getStudentPhone() {
		return this.studentPhone;
	}

	public void setStudentPhone (String studentPhone) {
		this.studentPhone = studentPhone;
	}
	
	
}
