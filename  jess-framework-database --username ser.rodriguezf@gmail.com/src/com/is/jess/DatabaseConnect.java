package com.is.jess;


import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import com.is.util.HibernateUtil;
import jess.*;

public class DatabaseConnect implements Userfunction {

	private String name = "database-connect";
	public Session session;
	
	public Value call(ValueVector vv, Context context) throws JessException {
		
		String loc="", location = vv.get(1).stringValue(context);
		String nam="", name = vv.get(2).stringValue(context);
		String typ, type = vv.get(3).stringValue(context);
		String usr="", username = vv.get(4).stringValue(context);
		String pss="", password = vv.get(5).stringValue(context);
		String dialect="", aux;
		Boolean flag=false;
		
		Configuration conf = new Configuration().configure("hibernate.cfg.xml");
		
		location = "jdbc:mysql:/"+location;
		loc = conf.getProperty("hibernate.connection.url");
		
		aux = conf.getProperty("hibernate.connection.url");
		for (int i=aux.lastIndexOf("/")+1; i<aux.length(); i++) 
			nam+=aux.charAt(i);
		
		if (type.equals("MySQL")) dialect = "org.hibernate.dialect.MySQLDialect";
		typ = conf.getProperty("hibernate.dialect");
		
		usr = conf.getProperty("hibernate.connection.username");
		pss = conf.getProperty("hibernate.connection.password");
		
		if ((location.equals(loc)) && (name.equals(nam)) && (dialect.equals(typ)) && (username.equals(usr)) && (password.equals(pss)))
			flag = true;
		
		if (flag) {
			Session session = HibernateUtil.getSessionFactory().openSession();
			return Funcall.TRUE;
		}
		else {return Funcall.FALSE;}
		
	}

	
	public String getName() {
		return name;
	}

}
