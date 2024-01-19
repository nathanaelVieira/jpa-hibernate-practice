package br.com.vieira.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.algaworks.ecommerce.listeners.GerarNotaFiscalListener;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * <h2>Classe Pedido</h2>
 * <p>
 * Uma classe usada como entidade de negócio.
 * 
 * @optional: cliente
 *            </p>
 */
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
/*
 * Annotation para ouvintes
 */
@EntityListeners({ GerarNotaFiscalListener.class })
@Entity
@Table(name = "pedido")
@ToString(onlyExplicitlyIncluded = true)

public class Pedido {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne(optional = false)
	@JoinColumn(name = "cliente_id")
	@ToString.Include
	private Cliente cliente;

	@OneToMany(mappedBy = "pedido")
	private List<ItemPedido> itens;

	@Column(name = "data_pedido")
	@ToString.Include
	private LocalDateTime dataPedido;

	@Column(name = "data_conclusao")
	@ToString.Include
	private LocalDateTime dataConclusao;

	@OneToOne(mappedBy = "pedido")
	@ToString.Include
	private NotaFiscal notaFiscal;

	@ToString.Include
	private BigDecimal total;

	@Enumerated(EnumType.STRING)
	@ToString.Include
	private StatusPedido status;

	@OneToOne(mappedBy = "pedido")
	@ToString.Include
	private Pagamento pagamento;

	@Embedded
	@ToString.Include
	private EnderecoEntregaPedido enderecoEntrega;

	public boolean isPago() {
		return StatusPedido.PAGO.equals(status);
	}
}
