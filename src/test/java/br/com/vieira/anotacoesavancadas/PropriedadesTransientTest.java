package br.com.vieira.anotacoesavancadas;

import org.junit.Assert;
import org.junit.Test;

import br.com.vieira.connection.EntityManagerConnectionTest;
import br.com.vieira.model.Cliente;

public class PropriedadesTransientTest extends EntityManagerConnectionTest {

	@Test
	public void testandoTransientAnnotation() {
		Cliente cliente = entityManager.find(Cliente.class, 1);
		System.out.println(cliente.getNomeFamilia());
		Assert.assertNotNull(cliente.getNomeFamilia());
	}
}
