package br.com.vieira.model;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyColumn;
import javax.persistence.OneToMany;
import javax.persistence.PostLoad;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@Table(name = "cliente")
@ToString(onlyExplicitlyIncluded = true)
@SecondaryTable(name = "cliente_detalhe", pkJoinColumns = @PrimaryKeyJoinColumn(name = "cliente_id"))
@AllArgsConstructor
@NoArgsConstructor
public class Cliente extends EntidadeBaseInteger {

	@ToString.Include
	private String nome;

	@Transient
	private String nomeFamilia;

	@ElementCollection
	@CollectionTable(name = "cliente_contato", joinColumns = @JoinColumn(name = "cliente_id"))
	@MapKeyColumn(name = "tipo")
	@Column(name = "contato")
	private Map<String, String> contatos;

	@Enumerated(EnumType.STRING)
	@ToString.Include
	@Column(table = "cliente_detalhe")
	private SexoCliente sexo;

	@Column(name = "data_nascimento", table = "cliente_detalhe")
	private LocalDate dataNascimento;

	@OneToMany(mappedBy = "cliente")
	private List<Pedido> pedidos;

	@ElementCollection // Criando coleções de dados.
	@CollectionTable(name = "produto_tag", joinColumns = @JoinColumn(name = "cliente_id"))
	@Column(name = "tag")
	private List<String> tags;

	@PostLoad
	private void configurarNomeDeFamilia() {
		if (nome != null && !nome.isBlank()) {
			int index = nome.indexOf(" ");
			nomeFamilia = nome.substring(index, nome.length()).trim();
		}
	}

}
