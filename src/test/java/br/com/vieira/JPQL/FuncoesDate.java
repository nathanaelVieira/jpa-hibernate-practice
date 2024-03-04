package br.com.vieira.JPQL;

import javax.persistence.TypedQuery;

import org.junit.Assert;
import org.junit.Test;

import br.com.vieira.connection.EntityManagerConnectionTest;

public class FuncoesDate extends EntityManagerConnectionTest {

	@Test
	public void operandoComFuncoesDateJPQL() {
		String queryFunctionDate = "select current_time, current_date, current_timestamp from Produto p where p.id = 6";

		TypedQuery<Object[]> query = entityManager.createQuery(queryFunctionDate, Object[].class);
		Object[] result = query.getSingleResult();

		Assert.assertNotNull(result);

		System.out.printf("%s | %s | %s%n", result[0], result[1], result[2]);
		// output: 07:22:37 | 2024-02-15 | 2024-02-16 07:22:37.0
	}

	@Test
	public void labutandoOperacoesComDate() {
		String queryJPQL = "select second(p.dataUltimaAtualizacao) from Produto p where p.id = 7";
		
//		year(p.dataUltimaAtualizacao)
//		month(p.dataUltimaAtualizacao)
//		day(p.dataUltimaAtualizacao)
//		hour(p.dataUltimaAtualizacao)
//		minute(p.dataUltimaAtualizacao)
//		second(p.dataUltimaAtualizacao)

		TypedQuery<Object> query = entityManager.createQuery(queryJPQL, Object.class);
		Object result = query.getSingleResult();
		
		System.out.println(result);
	}
}
