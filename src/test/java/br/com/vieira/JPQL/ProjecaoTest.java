package br.com.vieira.JPQL;

import java.util.List;

import javax.persistence.TypedQuery;

import org.junit.Assert;
import org.junit.Test;

import br.com.vieira.connection.EntityManagerConnectionTest;

public class ProjecaoTest extends EntityManagerConnectionTest {

	@Test
	public void projecaoCASE() {
		String jpqlCASE = """
				select
					p.dataConclusao,
					case
						when p.dataConclusao is not null then 'Data concluida'
						else 'Data pendente'
					end
				from
					Pedido p
				""";
		TypedQuery<Object[]> query = entityManager.createQuery(jpqlCASE, Object[].class);
		List<Object[]> resultList = query.getResultList();

		Assert.assertNotNull(resultList.isEmpty());

		resultList.forEach(element -> {
			System.out.println(element[0] + ", " + element[1]);
		});

	}
}
