package com.is.students;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.is.util.HibernateUtil;

import jess.*;

public class SaveStudent implements Userfunction {
	
	String name;
	
	public SaveStudent () { ;}
	
	public Value call(ValueVector vv, Context context) throws JessException {
		
		String student = vv.get(1).stringValue(context);
		saveInTable (student);
		Rete r = new Rete();
		//Deftemplate d = new Deftemplate("students", "Students table in DB", r);
		//d.addSlot("student-name", Funcall.NIL, "STRING");
		//r.addDeftemplate(d);
		System.out.println("Salida"+r.findDeftemplate("students"));
		Fact f = new Fact ("students", r);
		f.setSlotValue("student-name", new Value (student, RU.STRING));
		r.assertFact(f);
		return Funcall.TRUE;
		
	}	

	
	public String getName() {
		// TODO Auto-generated method stub
		return "save-student";
	}

	public void saveInTable (String studentName) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		Long studentId = null;
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
	}	
	
	public static void main(String[] argv) throws JessException
    {
      
      /*Rete r = new Rete();
      //r.executeCommand("(deftemplate Students (slot studentName))");
      Value v = r.executeCommand("(assert (students (student-name manuel)))");
      r.addUserfunction(new SaveStudent());
      r.executeCommand("(save-student manolito)");
      //Rete engine = new Rete();
      //Value v = engine.executeCommand("(assert (color red))");
      //Fact f = v.factValue(r.getGlobalContext());
      Value v2 = r.executeCommand("(facts)");
    } */
    }
}	
