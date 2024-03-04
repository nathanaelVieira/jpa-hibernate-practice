package br.com.vieira.JPQL;

import java.util.List;

import javax.persistence.TypedQuery;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mockito;

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
	
	@Test
	public void fooTest() {
		var runtime = Mockito.mock(Runtime.class);
		Mockito.when(runtime.totalMemory()).thenReturn(1_000L);
	}
	
	/**
	 *  Resolução de exercicio: (Não tenho dados no banco)
	 	
	 	select 
	 		ped 
	 	from 
	 		Pedido ped
	 	where 
	 		ped.id 
	 	in 
	 		(select 
	 			item.id 
	 		 from 
	 		 	ped.itens item 
	 		 join 
	 		 	item.produto prod
	 		 join 
	 		 	prod.categorias cate
	 		 where 
	 			cate.id = 2
	 		)
	 		
	 */
	
}
