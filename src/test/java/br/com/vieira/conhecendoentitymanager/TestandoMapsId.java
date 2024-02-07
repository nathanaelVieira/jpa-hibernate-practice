package br.com.vieira.conhecendoentitymanager;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import br.com.vieira.connection.EntityManagerConnectionTest;
import br.com.vieira.model.NotaFiscal;
import br.com.vieira.model.PagamentoCartao;
import br.com.vieira.model.Pedido;
import br.com.vieira.model.StatusPagamento;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestandoMapsId extends EntityManagerConnectionTest {

	/**
	 * Esse teste insere uma nota fiscal apenas uma vez ao banco de dados. Teste
	 * unitário para execução uma unica vez. Sua chave já esta populada.
	 */
	@Test
	@Ignore
	public void inserirItemPedidoNoBancoDeDados() {

		// Cenario
		Pedido pedido = entityManager.find(Pedido.class, 7);

		NotaFiscal notaFiscal = new NotaFiscal();
		notaFiscal.setPedido(pedido);
		notaFiscal.setDataEmissao(null);
//		notaFiscal.setXml("</xml>");

		// Execução
		entityManager.getTransaction().begin();
		entityManager.persist(notaFiscal);
		entityManager.getTransaction().commit();
		entityManager.clear();

		// Verificação
		Assert.assertNotNull(entityManager.find(NotaFiscal.class, notaFiscal.getId()));
		Assert.assertEquals(pedido.getId(), notaFiscal.getId());
	}

	@Test
	@Ignore
	public void testandoPagamentoComCartao() {
		Pedido pedido = entityManager.find(Pedido.class, 10);
		if (pedido != null) {
			PagamentoCartao pagamento = new PagamentoCartao();
//			pagamento.setNumero("invalid number");
			pagamento.setPedido(pedido);
			pagamento.setStatus(StatusPagamento.RECEBIDO);
			entityManager.persist(pagamento);
		}

		entityManager.getTransaction().begin();
		entityManager.getTransaction().commit();

		entityManager.clear();

		PagamentoCartao pagamentoCartao = entityManager.find(PagamentoCartao.class, pedido.getId());
		Assert.assertNotNull(pagamentoCartao);
	}
}
