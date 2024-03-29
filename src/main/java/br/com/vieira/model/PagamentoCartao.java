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
@DiscriminatorValue(value = "cartao")
@Table(name = "pagamento_cartao")
public class PagamentoCartao extends Pagamento {

	@Column(name = "numero_cartao", nullable = false)
	private String numeroCartao;
}
