package com.is.jess;


import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.lang.String;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import org.hibernate.mapping.PersistentClass;
import org.hibernate.mapping.Property;
import org.hibernate.persister.entity.EntityPersister;

import com.is.students.Students;
import com.is.util.HibernateUtil;
import jess.*;

@SuppressWarnings("deprecation")
public class LoadDatabase implements Userfunction {
	private String name = "load-database";
	
	public Value call(ValueVector vv, Context context) throws JessException {
		
		Rete r = context.getEngine();
		SessionFactory session = new AnnotationConfiguration().configure()
		.buildSessionFactory();
		Session s = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		transaction = s.beginTransaction();
		Map metadata = session.getAllClassMetadata(); 
		
		for (Iterator i = metadata.values().iterator(); i.hasNext();) { 
			
			EntityPersister persister = (EntityPersister) i.next();
			String entityName = persister.getEntityName();
			String classNameUpper = entityName.substring(entityName.lastIndexOf(".")+1, entityName.length());
			String classNameLower = classNameUpper.toLowerCase();
			r.defclass(classNameLower, entityName, null);
			
			try {
                 List list = s.createQuery("from "+classNameUpper).list();
                 for (Iterator iterator = list.iterator(); iterator.hasNext();)
                 {
                         Object instance = iterator.next();
                         r.definstance(classNameLower, instance, false, context);
                 }
         } catch (HibernateException e) {
                 transaction.rollback();
                 e.printStackTrace();
         }
		}
		transaction.commit();
			
		return null;
	}

	@Override
	public String getName() {
		
		return name;
	}


}
