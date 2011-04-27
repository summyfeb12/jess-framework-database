package com.is.students;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="COURSE")
public class Course {

	private Long courseId;
	private String courseName;

	public Course() {
	}

	public Course(String courseName) {
		this.courseName = courseName;
	}

	@Id
	@GeneratedValue
	@Column(name="COURSE_ID")
	public Long getCourseId() {
		return this.courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}

	@Column(name="COURSE_NAME", nullable=false)
	public String getCourseName() {
		return this.courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

}
