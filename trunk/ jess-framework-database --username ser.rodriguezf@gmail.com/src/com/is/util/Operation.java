package com.is.util;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.is.students.Course;
import com.is.students.Student;

public class Operation {

	Object instance;
	private long Id=0;
	private boolean flag = false;
	
	public Operation (Student student) {
		
		instance = student;
	}
	
	public Operation (Course course) {
		
		instance = course;
	}
	
	public static Course Seek (String courseName) {
		
		Course course=null;
		Course courseAux=null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			List courses = session.createQuery("from Course").list();
			for (Iterator iterator = courses.iterator(); iterator.hasNext();)
			{
				course = (Course) iterator.next();
				if (course.getCourseName().equals(courseName)) {
					courseAux = course;
					
					break;
				}	
			}
			transaction.commit();
		}
		catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		}
		session.close();
		return courseAux;
	
	}
	
	public static int isCourse (String courseName) {
			
			Course course=null;
			int value = 0;
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction transaction = null;
			try {
				transaction = session.beginTransaction();
				List courses = session.createQuery("from Course").list();
				for (Iterator iterator = courses.iterator(); iterator.hasNext();)
				{
					course = (Course) iterator.next();
					if (course.getCourseName().equals(courseName)) {
						value = 1;
						break;
					}	
				}
				transaction.commit();
			}
			catch (HibernateException e) {
				transaction.rollback();
				e.printStackTrace();
			}
			session.close();
			return value;
		
		}
	 
	
	public void Save() {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Id = (Long) session.save(instance);
		}
		catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		}
		transaction.commit();
		session.close();
	}
	
	public long getId () {
		return this.Id;

	}
}	