package br.com.vieira.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@DiscriminatorValue(value = "boleto")
@Table(name = "pagamento_boleto")
public class PagamentoBoleto extends Pagamento {

	@Column(name = "codigo_barras", nullable = false)
	private String codigoBarras;
}
