package br.com.vieira.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ForeignKey;
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

/**
 * <h2>Classe Pedido</h2>
 * <p>
 * Uma classe usada como entidade de negócio.
 * 
 * @optional: cliente
 * @not-null: 
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
public class Pedido {
	
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne(optional = false, cascade = CascadeType.PERSIST)
	@JoinColumn(name = "cliente_id", foreignKey = @ForeignKey(name = "fk_pedido_cliente"))
	private Cliente cliente;

	@OneToMany(mappedBy = "pedido", cascade = { CascadeType.MERGE, CascadeType.PERSIST })
	private List<ItemPedido> itens;

	@Column(name = "data_pedido")
	private LocalDateTime dataPedido;

	@Column(name = "data_conclusao")
	private LocalDateTime dataConclusao;

	@OneToOne(mappedBy = "pedido")
	private NotaFiscal notaFiscal;

	private BigDecimal total;

	@Enumerated(EnumType.STRING)
	private StatusPedido status;

	@OneToOne(mappedBy = "pedido")
	private Pagamento pagamento;

	@Embedded
	private EnderecoEntregaPedido enderecoEntrega;

	public boolean isPago() {
		return StatusPedido.PAGO.equals(status);
	}
}
