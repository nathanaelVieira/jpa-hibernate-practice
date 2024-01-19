package br.com.vieira.anotacoesavancadas;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

import br.com.vieira.connection.EntityManagerConnectionTest;
import br.com.vieira.model.Cliente;

public class ElementCollectionTest extends EntityManagerConnectionTest {

	@Test
	public void devePersistirElementoTags() {
		// Cenario
		Cliente cliente = entityManager.find(Cliente.class, 1);

		// Execução
		entityManager.getTransaction().begin();
		cliente.setTags(Arrays.asList("Bonus", "Premiun"));
		entityManager.getTransaction().commit();
		entityManager.clear();

		// Verificação
		Cliente clienteBusca = entityManager.find(Cliente.class, 1);
		Assert.assertNotNull(clienteBusca.getPedidos());

	}
}
