package br.com.vieira.conhecendoentitymanager;

import org.junit.Test;

import br.com.vieira.connection.EntityManagerConnectionTest;

public class TesteGerenciandoTransacoes extends EntityManagerConnectionTest {

	@Test
	public void deveAbrirEFecharUmaTransacao() {
		entityManager.getTransaction().begin();
		
//		entityManager.getTransaction().rollback();
		
		entityManager.getTransaction().commit();
	}
}
