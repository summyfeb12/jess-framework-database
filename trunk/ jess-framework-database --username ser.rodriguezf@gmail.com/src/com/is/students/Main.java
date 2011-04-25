package com.is.students;


import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.is.util.HibernateUtil;

public class Main {

	public static void main(String[] args) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();

			Set<Course> courses = new HashSet<Course>();
			courses.add(new Course("Maths"));
			courses.add(new Course("Computer Science"));

			Student student1 = new Student("Eswar", courses);
			Student student2 = new Student("Joe", courses);
			session.save(student1);
			session.save(student2);
			 try {
                
                 List list = session.createQuery("from Student").list();
                 for (Iterator iterator = list.iterator(); iterator.hasNext();)
                 {
                         Student instance = (Student) iterator.next();
                         System.out.println(instance.getStudentName());
                         //r.definstance(classNameLower, instance, true, context);
                 }
                 transaction.commit();
         } catch (HibernateException e) {
                 transaction.rollback();
                 e.printStackTrace();
         }
		
		} finally {
			session.close();
		}

	}
}
