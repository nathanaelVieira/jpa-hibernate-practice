package br.com.vieira.conhecendoentitymanager;

import static org.junit.Assert.assertTrue;

import org.junit.Ignore;
import org.junit.Test;

import br.com.vieira.builders.ClienteBuilder;
import br.com.vieira.builders.EnderecoEntregaPedidoBuilder;
import br.com.vieira.builders.EstoqueBuilder;
import br.com.vieira.builders.PedidoBuilder;
import br.com.vieira.model.Cliente;
import br.com.vieira.model.EnderecoEntregaPedido;
import br.com.vieira.model.Estoque;
import br.com.vieira.model.Pedido;

public class TestandoBuildersModelo {

//@formatter:off
	@Test
	@Ignore
	public void deveCriarUmClienteBuilder() {
		Cliente agora = ClienteBuilder
				.umCliente().comConfiguracaoPadrao().agora();
		Cliente agora2 = ClienteBuilder
				.umCliente().comConfiguracaoPadrao().agora();
		System.out.println(agora);
		System.out.println(agora2);
	}

	@Test
	@Ignore
	public void deveCriarUmEnderecoEntregaPedidoBuilder() {
		EnderecoEntregaPedido agora = EnderecoEntregaPedidoBuilder
				.umEnderecoEntregaPedido()
				.comConfiguracaoPadrao()
				.agora();
		EnderecoEntregaPedido agora2 = EnderecoEntregaPedidoBuilder
				.umEnderecoEntregaPedido()
				.comConfiguracaoPadrao()
				.agora();
		System.out.println(agora);
		System.out.println(agora2);
		
		boolean mesmoValorCorrespondente = (agora instanceof EnderecoEntregaPedido) && (agora2 instanceof EnderecoEntregaPedido);
		assertTrue(mesmoValorCorrespondente);
	} 
	
	
	@Test
	public void deveCriarUmEstoqueBuilder() {
		Estoque agora = EstoqueBuilder.umEstoque().comConfiguracaoPadrao().agora();
		boolean valorCorrespondente = (agora instanceof Estoque);
		assertTrue(valorCorrespondente);
	}
	
	@Test
	public void deveCriarUmPedidoBuilder()	{
		Pedido agora = PedidoBuilder.umPedido().comConfiguracaoPadrao().agora();
		assertTrue(agora instanceof Pedido);
	}
//@formatter:on
}
