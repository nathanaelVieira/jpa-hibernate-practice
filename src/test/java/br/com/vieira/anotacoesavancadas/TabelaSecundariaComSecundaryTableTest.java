package br.com.vieira.anotacoesavancadas;

import java.time.LocalDate;

import org.junit.Assert;
import org.junit.Test;

import br.com.vieira.connection.EntityManagerConnectionTest;
import br.com.vieira.model.Cliente;
import br.com.vieira.model.SexoCliente;

public class TabelaSecundariaComSecundaryTableTest extends EntityManagerConnectionTest {

	@Test
	public void salvandoCliente() {
		Cliente cliente = new Cliente();
		cliente.setNome("Cliente Teste Tabela Secundaria.");
		cliente.setSexo(SexoCliente.NAO_ESPECIFICADO);
		cliente.setDataNascimento(LocalDate.parse("1999-02-28"));

		entityManager.getTransaction().begin();
		entityManager.persist(cliente);
		entityManager.getTransaction().commit();
		entityManager.clear();

		Cliente busca = entityManager.find(Cliente.class, cliente.getId());
		Assert.assertNotNull(busca.getSexo());
	}
}
