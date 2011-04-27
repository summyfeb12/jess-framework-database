package com.is.util;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import jess.Context;
import jess.Fact;
import jess.JessException;
import jess.Rete;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.is.students.Course;
import com.is.students.Student;

public class Operation {

	Object instance;
	private Long id=null;
	private boolean flag = false;
	
	public Operation () {
		
	}
	
	public Operation (Student student) {
		
		instance = student;
	}
	
	public Operation (Course course) {
		
		instance = course;
	}
	
	public static Course seek (String courseName) {
		
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
	
	public static int find (Long id, String mode) {
		
		Course course=null;
		Student student=null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
			try {
				transaction = session.beginTransaction();
				if (mode.equals("student"))
					student = (Student) session.get(Student.class, id);
				else if (mode.equals("course"))
					course = (Course) session.get(Course.class, id);
				transaction.commit();
			} catch (HibernateException e) {
				transaction.rollback();
				e.printStackTrace();
			} finally {
				session.close();
			}
		if ((course!=null) || (student!=null) ) {return 1;}
		else {return 0;}
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
	 
	
	public void save() {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			id = (Long) session.save(instance);
		}
		catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		}
		transaction.commit();
		session.close();
	}
	
	public void updateStudent (Long id, String studentName, Set<Course> courses) {
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Student student = (Student) session.get(Student.class, id);
			student.setStudentName(studentName);
			student.setCourses(courses);
			transaction.commit();
		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		
	}
	
	public void courseUpdate (Long courseId, String courseName) {
			
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction transaction = null;
			try {
				transaction = session.beginTransaction();
				Course course = (Course) session.get(Course.class, courseId);
				course.setCourseName(courseName);
				transaction.commit();
			} catch (HibernateException e) {
				transaction.rollback();
				e.printStackTrace();
			} finally {
				session.close();
			}
			
	}
	
	public void delete (Long id, String mode) {
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			if (mode.equals("course")){
				Course course = (Course) session.get(Course.class, id);
				session.delete(course);
			} 
			else if (mode.equals("student")) {
				Student student = (Student) session.get(Student.class, id);
				session.delete(student);
			}
			transaction.commit();
		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		
	}
	
	public static Fact findFact (Long id, String mode, Context context) throws JessException {
		
		Rete r = context.getEngine();
		Iterator iterator = r.listFacts();
		String slotValue = mode.concat("Id"); 
		String template = "MAIN::";
		

		while (iterator.hasNext()) {
			
			Fact f = (Fact) iterator.next();
			if (f.getDeftemplate().getName().equals(template.concat(mode))) {
				Long idValue = f.getSlotValue(slotValue).longValue(context);
				if (id == idValue) {
					return f;
				}
			}
		}
		return null;
			
	}
	
	public Long getId () {
		return this.id;

	}
}	