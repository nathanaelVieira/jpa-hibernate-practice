package br.com.vieira.builders;

import java.math.BigDecimal;

import br.com.vieira.model.ItemPedido;

public class ItemPedidoBuilder {
	private ItemPedido itemPedido;

	private ItemPedidoBuilder() {

	}

	public static ItemPedidoBuilder umItemPedido() {
		ItemPedidoBuilder builder = new ItemPedidoBuilder();
		builder.itemPedido = new ItemPedido();
		return builder;
	}

	public ItemPedidoBuilder comConfiguracaoPadrao() {
		itemPedido.setPedido(PedidoBuilder.umPedido().comConfiguracaoPadrao().agora());
		itemPedido.setPrecoProduto(new BigDecimal(9999.99));
		itemPedido.setProduto(ProdutoBuilder.umProduto().comConfiguracaoPadrao().agora());
		return this;
	}

	public ItemPedido agora() {
		return itemPedido;
	}
}
