package br.com.caelum.vraptor.paginator.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

import br.com.caelum.vraptor.paginator.User;

@SuppressWarnings("deprecation")
public class HibernateDatabaseTest {

	private static SessionFactory factory;
	
	@AfterClass
	public static void shutdown() {
		factory.close();
	}
	
	@BeforeClass
	public static void startup() {
		factory = new AnnotationConfiguration().configure().addAnnotatedClass(User.class).buildSessionFactory();
	}

	protected Session session;

	@After
	public void clearDatabase() {
		session.createQuery("delete from User").executeUpdate();
		session.close();
		session = null;
	}
	
	@Before
	public void setupDatabase() {
		session = factory.openSession();
		Transaction tx = session.beginTransaction();
		for(int i=1;i<=100;i++) {
			session.save(new User(i, "User " + i));
		}
		tx.commit();
	}
	

}
