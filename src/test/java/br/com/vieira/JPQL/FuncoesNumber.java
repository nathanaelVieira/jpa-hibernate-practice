package br.com.vieira.JPQL;

import java.util.List;

import javax.persistence.TypedQuery;

import org.junit.Test;

import br.com.vieira.connection.EntityManagerConnectionTest;

public class FuncoesNumber extends EntityManagerConnectionTest {

	@Test
	public void labutandoABS() {
		String queryJPQL = "select abs(-10) from Produto p";

		TypedQuery<Object> query = entityManager.createQuery(queryJPQL, Object.class);
		Object result = query.getSingleResult();

		System.out.println(result);
	}

	@Test
	public void labutandoMOD() {
		String queryJPQL = "select mod(3,2) from Produto p";

		TypedQuery<Object> query = entityManager.createQuery(queryJPQL, Object.class);
		Object result = query.getSingleResult();

		System.out.println(result);
	}

	@Test
	public void labutandoSQRT() {
		String queryJPQL = "select sqrt(3) from Produto p where p.id = 7";

		TypedQuery<Object> query = entityManager.createQuery(queryJPQL, Object.class);
		List<Object> resultList = query.getResultList();

		resultList.forEach(System.out::println);
	}
	
	@Test
	public void labutandoSQRT_MOD_ABS() {
		String queryJPQL = "select sqrt(3), mod(3,2), abs(-1000) from Produto p where p.id = 7";
		
		TypedQuery<Object[]> query = entityManager.createQuery(queryJPQL, Object[].class);
		Object[] singleResult = query.getSingleResult();
		
		System.out.printf("%f | %d | %d %n", singleResult[0], singleResult[1], singleResult[2]);
	}
}
