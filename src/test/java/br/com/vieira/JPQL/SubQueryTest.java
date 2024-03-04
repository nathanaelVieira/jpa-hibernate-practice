package br.com.vieira.JPQL;

import java.util.List;

import javax.persistence.TypedQuery;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import br.com.vieira.connection.EntityManagerConnectionTest;
import br.com.vieira.model.Pagamento;
import br.com.vieira.model.Produto;

public class SubQueryTest extends EntityManagerConnectionTest {

	@Test
	public void application() {

		String jpql = "select p from Produto p where p.preco = (select max(preco) from Produto)";

		TypedQuery<Produto> query = entityManager.createQuery(jpql, Produto.class);

		Produto singleResult = query.getSingleResult();
		System.out.println(singleResult.getPreco());

		Assert.assertNotNull(singleResult);
	}

	//@formatter:off
	@Test @Ignore
	public void applicationINSubQuery() {
		String jpql = "select p.pagamento from Pedido p";
		
		TypedQuery<Pagamento> query = entityManager.createQuery(jpql, Pagamento.class);
		List<Pagamento> resultList = query.getResultList();
		
		resultList.forEach(e -> System.out.println(e.toString()));
//		Assert.assertFalse(resultList.isEmpty());
	}
	//@formatter:on
}
