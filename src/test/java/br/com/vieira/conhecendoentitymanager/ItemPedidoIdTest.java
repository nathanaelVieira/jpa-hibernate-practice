package br.com.vieira.conhecendoentitymanager;

import org.junit.Ignore;
import org.junit.Test;

import br.com.vieira.builders.PedidoBuilder;
import br.com.vieira.builders.ProdutoBuilder;
import br.com.vieira.connection.EntityManagerConnectionTest;
import br.com.vieira.model.Cliente;
import br.com.vieira.model.ItemPedido;
import br.com.vieira.model.ItemPedidoId;
import br.com.vieira.model.Pedido;
import br.com.vieira.model.Produto;

public class ItemPedidoIdTest extends EntityManagerConnectionTest {
	@Test
	@Ignore
	public void deveRealizarPersistenciaComChaveComposta() {
		entityManager.getTransaction().begin();

		Cliente clienteTest = entityManager.find(Cliente.class, 1);

		Pedido pedido = PedidoBuilder.umPedido().comConfiguracaoPadraoSemCliente()
				.umClienteRelacionadoDoBancoDeDados(clienteTest).agora();
		Produto produto = ProdutoBuilder.umProduto().comConfiguracaoPadraoSemEstoque().agora();

		entityManager.persist(pedido);
		entityManager.persist(produto);

		ItemPedido itemPedido = new ItemPedido();
		itemPedido.setId(new ItemPedidoId(pedido.getId(), produto.getId()));
		itemPedido.setPedido(pedido);
		itemPedido.setPrecoProduto(produto.getPreco());
		itemPedido.setProduto(produto);
		itemPedido.setQuantidade(null);

		entityManager.persist(itemPedido);
		entityManager.getTransaction().commit();

		entityManager.clear();
	}

}
