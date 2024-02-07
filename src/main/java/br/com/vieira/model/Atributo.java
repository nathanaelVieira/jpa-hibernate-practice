package br.com.vieira.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
@ToString
public class Atributo {
	@Column(length = 100, nullable = false)
	private String nome;
	private String valor;
}
