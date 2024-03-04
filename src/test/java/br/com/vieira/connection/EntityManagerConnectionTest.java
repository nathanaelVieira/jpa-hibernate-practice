package br.com.vieira.connection;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

public class EntityManagerConnectionTest {

	private static EntityManagerFactory entityManagerFactory;

	protected EntityManager entityManager;

	@BeforeClass
	public static void setUpBeforeClass() {
		entityManagerFactory = Persistence.createEntityManagerFactory("mysql-workbench");
	}

	public EntityTransaction getTransition() {
		EntityTransaction transaction = entityManager.getTransaction();
		return transaction;
	}

	@Before
	public void setUp() {
		entityManager = entityManagerFactory.createEntityManager();
	}

	@AfterClass
	public static void tearDownAfterClass() {
		entityManagerFactory.close();
	}

	@After
	public void tearDown() {
		entityManager.close();
	}

}
