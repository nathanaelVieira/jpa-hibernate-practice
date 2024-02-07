package br.com.vieira.operacaocascata;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import br.com.vieira.connection.EntityManagerConnectionTest;
import br.com.vieira.model.Cliente;
import br.com.vieira.model.Pedido;
import br.com.vieira.model.SexoCliente;

public class CascadeTypeTest extends EntityManagerConnectionTest {

	@Test
	/* Executado Apenas uma vez */ @Ignore
	public void realizandoPersistencia() {
		// Cenário
		Cliente cliente = new Cliente();
		cliente.setCpf("12345678900");
		cliente.setNome("João Azul Marinho");
		cliente.setSexo(SexoCliente.MASCULINO);

		Pedido pedido = new Pedido();
		pedido.setCliente(cliente);

		// Execução
		entityManager.getTransaction().begin();
		entityManager.persist(pedido);
		entityManager.getTransaction().commit();
		entityManager.clear();

		// Varificação
		Cliente clienteRecuperado = entityManager.find(Cliente.class, cliente.getId());
		Assert.assertNotNull(clienteRecuperado);
		Assert.assertEquals(clienteRecuperado.getCpf(), "123.456.789-00");

	}

	@Test
	public void verificandoFormatacaoCpf() {
		Cliente cliente = new Cliente();
		cliente.setCpf("12345678900");
		System.out.println(cliente.getCpf());
	}

}
