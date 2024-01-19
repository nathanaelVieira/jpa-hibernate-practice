package br.com.vieira.builders;

import java.time.LocalDateTime;

import br.com.vieira.model.Cliente;
import br.com.vieira.model.Pedido;

public class PedidoBuilder {

	private Pedido pedido;

	private static Integer contador = 1;

	private PedidoBuilder() {
	}

	public static PedidoBuilder umPedido() {
		PedidoBuilder builder = new PedidoBuilder();
		builder.pedido = new Pedido();
		return builder;
	}

//@formatter:off
	public PedidoBuilder comConfiguracaoPadrao() {
		pedido.setId(null);
		pedido.setCliente(ClienteBuilder
				.umCliente()
				.comConfiguracaoPadrao()
				.agora());
		pedido.setDataConclusao(LocalDateTime.now().plusDays(contador));
		pedido.setEnderecoEntrega(
				EnderecoEntregaPedidoBuilder
				.umEnderecoEntregaPedido()
				.comConfiguracaoPadrao()
				.agora());
		pedido.setDataPedido(LocalDateTime.now());
		return this;
	}
	
	public PedidoBuilder comConfiguracaoPadraoSemCliente() {
		pedido.setId(null);
		pedido.setDataConclusao(LocalDateTime.now().plusDays(contador));
		pedido.setEnderecoEntrega(
				EnderecoEntregaPedidoBuilder
				.umEnderecoEntregaPedido()
				.comConfiguracaoPadrao()
				.agora());
		pedido.setDataPedido(LocalDateTime.now());
		return this;
	}
	
	public PedidoBuilder umClienteRelacionadoDoBancoDeDados(Cliente cliente) {
		pedido.setCliente(cliente);
		return this;
	}

	public Pedido agora() {
		return pedido;
	}
//@formatter:on

}
