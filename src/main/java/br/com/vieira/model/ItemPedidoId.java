package br.com.vieira.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class ItemPedidoId implements Serializable {

	private static final long serialVersionUID = 1L;

	@EqualsAndHashCode.Include
	@Column(name = "pedido_id")
	private Integer pedidoId;
//	private Pedido pedido_id;

	@EqualsAndHashCode.Include
	@Column(name = "produto_id")
	private Integer produtoId;
//	private Produto produto_id;
}
