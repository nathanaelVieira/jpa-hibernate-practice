package br.com.vieira.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "nota_fiscal")
public class NotaFiscal {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue
	private Integer id;

	@MapsId
	@OneToOne(optional = false)
	@JoinColumn(name = "pedido_id", foreignKey = @ForeignKey(name = "fk_nota_fiscal_pedido"))
	private Pedido pedido;

	@Lob
	@Column(columnDefinition = "BLOB", nullable = false)
	private byte[] xml;
	
	@Temporal(TemporalType.TIMESTAMP) //TODO estudar Temporal
	@Column(name = "data_emissao", length = 6, nullable = false)
	private Date dataEmissao;
}
