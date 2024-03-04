package br.com.vieira.JPQL;

import javax.persistence.TypedQuery;

import org.junit.Test;

import br.com.vieira.connection.EntityManagerConnectionTest;

public class FuncoesParaFuncoesNATIVAS extends EntityManagerConnectionTest {

	@Test
	public void usandoFunction() {
		String queryJPQL = "select function('dayname', p.dataUltimaAtualizacao) from Produto p where p.id = 7";

		TypedQuery<String> query = entityManager.createQuery(queryJPQL, String.class);
		String result = query.getSingleResult();

		System.out.println(result);
	}
}
