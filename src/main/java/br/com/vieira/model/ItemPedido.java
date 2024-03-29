package br.com.vieira.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "item_pedido")
public class ItemPedido {

	@EmbeddedId
	private ItemPedidoId id;

	@MapsId("pedidoId")
	@ManyToOne(optional = false)
	@JoinColumn(name = "pedido_id", foreignKey = @ForeignKey(name = "fk_item_pedido_pedido"))
	private Pedido pedido;

	@MapsId("produtoId")
	@ManyToOne(optional = false)
	@JoinColumn(name = "produto_id", foreignKey = @ForeignKey(name = "fk_item_pedido_produto"))
	private Produto produto;

	@Column(name = "preco_produto", precision = 19, scale = 2, nullable = false)
	private BigDecimal precoProduto;

	@Column(nullable = false)
	private Integer quantidade;

	public ItemPedido(Pedido pedido, Produto produto, BigDecimal precoProduto, Integer quantidade) {
		this.pedido = pedido;
		id.setPedidoId(pedido.getId());
		id.setProdutoId(produto.getId());
		this.produto = produto;
		this.precoProduto = precoProduto;
		this.quantidade = quantidade;
	}

}
