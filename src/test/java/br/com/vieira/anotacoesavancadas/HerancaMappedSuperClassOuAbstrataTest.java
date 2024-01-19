package br.com.vieira.anotacoesavancadas;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import br.com.vieira.connection.EntityManagerConnectionTest;
import br.com.vieira.model.Pagamento;
import br.com.vieira.model.PagamentoBoleto;

public class HerancaMappedSuperClassOuAbstrataTest extends EntityManagerConnectionTest {

	@Test
	public void inserindoPagamento() {
		// Cenario
//		Cliente cliente = new Cliente();
//		cliente.setNome("Nathanael L. Vieira");
//		cliente.setSexo(SexoCliente.MASCULINO);
//		cliente.setDataNascimento(LocalDate.parse("1998-03-02"));
//
//		Pedido pedido = new Pedido();
//		
//		Pagamento pagamento = new PagamentoBoleto();
//		pagamento.setPedido(pedido);
//		pagamento.setStatus(StatusPagamento.PROCESSANDO);

		// Execução

//		entityManager.getTransaction().begin();
//		Cliente merge = entityManager.merge(cliente);
//		pedido.setCliente(merge);
//		entityManager.persist(pedido);
//		pagamento.setPedido(pedido);
//		entityManager.persist(pagamento);
//		pedido.setPagamento(pagamento);
//		entityManager.getTransaction().commit();

		// Validação
		Pagamento pagamento = entityManager.find(Pagamento.class, 11);
		assertNotNull(pagamento);
		assertTrue(pagamento instanceof PagamentoBoleto);
	}
}
