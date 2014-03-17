package br.com.caelum.vraptor.paginator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

public class JPADatabaseTest {

	private static EntityManagerFactory factory; 
	
	@AfterClass
	public static void shutdown() {
		factory.close();
	}
	
	@BeforeClass
	public static void startup() {
		factory = Persistence.createEntityManagerFactory("test");
	}

	protected EntityManager manager;

	@After
	public void clearDatabase() {
		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		manager.createQuery("delete from User").executeUpdate();
		tx.commit();
		manager.close();
		manager = null;
	}
	
	@Before
	public void setupDatabase() {
		manager = factory.createEntityManager();
		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		for(int i=1;i<=100;i++) {
			manager.persist(new User(i, "User " + i));
		}
		tx.commit();
	}
	

}
