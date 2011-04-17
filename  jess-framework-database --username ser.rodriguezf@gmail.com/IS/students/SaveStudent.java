package com.is.students;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.is.util.HibernateUtil;

import jess.*;

public class SaveStudent implements Userfunction {

	@Override
	public Value call(ValueVector vv, Context context) throws JessException {
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		Long studentId = null;
		String studentName = vv.get(1).stringValue(context);
		try {
			transaction = session.beginTransaction();
			Students student = new Students();
			student.setStudentName(studentName);
			studentId = (Long) session.save(student);
			transaction.commit();
		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		Rete r = new Rete();
		r.executeCommand("(assert (Student (nameStudent"+ vv.get(1).stringValue(context)+")))");
		return new Value(vv.get(1).stringValue(context));
		
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "SaveStudent";
	}

}
