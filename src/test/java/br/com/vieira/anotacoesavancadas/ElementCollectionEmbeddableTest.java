package br.com.vieira.anotacoesavancadas;

import static org.junit.Assert.assertNotNull;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import br.com.vieira.connection.EntityManagerConnectionTest;
import br.com.vieira.model.Atributo;
import br.com.vieira.model.Produto;

public class ElementCollectionEmbeddableTest extends EntityManagerConnectionTest {

	@Test
	public void deveRealizarAPersistenciaDeColecoesEmbeddable() {
		// Cenario
		Produto produto = entityManager.find(Produto.class, 1);
		List<Atributo> atributos = Arrays.asList(new Atributo("Cor", "Azul Bostil"), new Atributo("Peso", "2T"));

		// Execução
		entityManager.getTransaction().begin();
		if (produto.getAtributos().isEmpty())
			produto.setAtributos(atributos);
		entityManager.getTransaction().commit();
		entityManager.clear();

		// Verificação
		Produto produto2 = entityManager.find(Produto.class, 1);
		produto2.getAtributos().forEach(e -> System.out.println(e.toString()));
		assertNotNull(produto2.getAtributos());
	}
}
