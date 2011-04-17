package com.is.students;

import java.util.List;
import java.util.Iterator;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.is.util.HibernateUtil;

public class Main {

	public static void main(String[] args) {
		Main obj = new Main();
		@SuppressWarnings("unused")
		Long studentId1 = obj.saveStudent("Manuel");
		Long studentId2 = obj.saveStudent("Paco");
		Long studentId3 = obj.saveStudent("Matias");
		obj.listStudents();
		obj.updateCourse(studentId3, "Raul");
		obj.deleteCourse(studentId2);
		obj.listStudents();
	}
	
	public Long saveStudent(String StudentName)
	{
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		Long studentId = null;
		try {
			transaction = session.beginTransaction();
			Students student = new Students();
			student.setStudentName(StudentName);
			studentId = (Long) session.save(student);
			transaction.commit();
		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return studentId;
	}
	
	public void listStudents()
	{
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			List students = session.createQuery("from Students").list();
			for (Iterator iterator = students.iterator(); iterator.hasNext();)
			{
				Students course = (Students) iterator.next();
				System.out.println(course.getStudentName());
			}
			transaction.commit();
		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
	
	public void updateCourse(Long courseId, String courseName)
	{
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Students course = (Students) session.get(Students.class, courseId);
			course.setStudentName(courseName);
			transaction.commit();
		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
	
	public void deleteCourse(Long courseId)
	{
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Students course = (Students) session.get(Students.class, courseId);
			session.delete(course);
			transaction.commit();
		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
}
