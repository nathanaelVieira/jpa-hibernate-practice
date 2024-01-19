package br.com.vieira.conhecendoentitymanager;

import org.junit.Assert;
import org.junit.Test;

import br.com.vieira.connection.EntityManagerConnectionTest;
import br.com.vieira.model.Produto;

public class TesteConceitoPersistencia extends EntityManagerConnectionTest {

	@Test
	public void deveRealizarATransacaoSemRiscos() {
		Produto produto = entityManager.find(Produto.class, 4);
		Assert.assertNotNull(produto);
		entityManager.clear();
	}

}
