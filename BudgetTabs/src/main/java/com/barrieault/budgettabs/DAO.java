package com.barrieault.budgettabs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.Entity;
import javax.persistence.NoResultException;
import javax.validation.ConstraintViolationException;

import org.hibernate.NonUniqueResultException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.SharedSessionContract;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.jasypt.util.password.BasicPasswordEncryptor;

import com.barrieault.budgettabs.User;

public class DAO {
	private static SessionFactory factory;
	
	//connection to mysql program
	private static void setupFactory() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (Exception e) {
			
		}
		
		//connecting to mysql database
		Configuration configuration = new Configuration();
		// matching POJO created to hibernate
		configuration.configure("hibernate.cfg.xml");
		configuration.addResource("user.hbm.xml");
		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties()).build();
		factory = configuration.buildSessionFactory(serviceRegistry);
	}
	
	//hibernate session for user login 
	public static User userAndPassValidator(User user){
		if (factory == null)
			setupFactory();
		//get current session 
		Session hibernateSession = factory.openSession();
		//Transaction begins
		hibernateSession.getTransaction().begin();
		try {
			String query = "FROM user WHERE username='" + user.getUsername() + "'";
			User singleUser = (User) hibernateSession.createQuery(query).getSingleResult();
			BasicPasswordEncryptor passwordEncryptor = new BasicPasswordEncryptor();
			if (passwordEncryptor.checkPassword(user.getPassword(), singleUser.getPassword())){
				hibernateSession.getTransaction().commit();
				hibernateSession.close();
				return singleUser;
			} else {
				hibernateSession.getTransaction().commit();
				hibernateSession.close();
				return null;
			}
		} catch (Exception e) {
			System.out.println("Exception: " + e);
			hibernateSession.getTransaction().commit();
			hibernateSession.close();
			}
		
		
		return null;
	}

	public static boolean isUsernameTaken(User user) {
		if (factory == null)
			setupFactory();
		//get current session
		Session hibernateSession = factory.openSession();
		hibernateSession.getTransaction().begin();
		try {
			String query = "FROM user WHERE username='" + user.getUsername() + "'";
			User singleUser = (User) hibernateSession.createQuery(query).getSingleResult();
			System.out.println("Found: " + singleUser.getUsername());
		} catch (Exception e) {

			System.out.println("Exception: " + e);
			hibernateSession.close();  
			return false;
			
		}
		// no exception = there was a single result
		hibernateSession.close(); 
		return true;
	}
	//adding user to database and encrypting their password
	public static String addUser(User u) {
		if (factory == null)
			setupFactory();
		BasicPasswordEncryptor passwordEncryptor = new BasicPasswordEncryptor();
		u.setPassword(passwordEncryptor.encryptPassword(u.getPassword()));
		Session hibernateSession = factory.openSession();
		hibernateSession.getTransaction().begin();
		hibernateSession.save(u);
		hibernateSession.getTransaction().commit();
		hibernateSession.close();

		return null;
	}
	
}
