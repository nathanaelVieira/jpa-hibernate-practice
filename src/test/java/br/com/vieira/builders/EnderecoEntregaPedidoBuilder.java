package br.com.vieira.builders;

import br.com.vieira.model.EnderecoEntregaPedido;

public class EnderecoEntregaPedidoBuilder {

	private EnderecoEntregaPedido enderecoEntregaPedido;

	private static Integer contador = 0;

	private EnderecoEntregaPedidoBuilder() {
	}

	public static EnderecoEntregaPedidoBuilder umEnderecoEntregaPedido() {
		EnderecoEntregaPedidoBuilder builder = new EnderecoEntregaPedidoBuilder();
		builder.enderecoEntregaPedido = new EnderecoEntregaPedido();
		return builder;
	}

	public EnderecoEntregaPedidoBuilder comConfiguracaoPadrao() {
		enderecoEntregaPedido.setBairro(String.format("#%d Bairro", contador));
		enderecoEntregaPedido.setCep(String.format("#%d Cep", contador));
		enderecoEntregaPedido.setCidade(String.format("#%d Cidade", contador));
		enderecoEntregaPedido.setComplemento(String.format("#%d Complemento", contador));
		enderecoEntregaPedido.setEstado(String.format("#%d Estado", contador));
		enderecoEntregaPedido.setLogradouro(String.format("#%d Logradouro", contador));
		enderecoEntregaPedido.setNumero(contador.toString());
		return this;
	}

	public EnderecoEntregaPedido agora() {
		return enderecoEntregaPedido;
	}

}
