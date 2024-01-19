package br.com.vieira.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.PostLoad;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "produto")
public class Produto {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String nome;

	private String descricao;

	private BigDecimal preco;

	@Lob
	private byte[] image;

	@Column(name = "data_criacao", updatable = false)
	private LocalDateTime dataCriacao;

	@Column(name = "data_ultima_atualizacao", insertable = false)
	private LocalDateTime dataUltimaAtualizacao;

	@ManyToMany
	@JoinTable(name = "produto_categoria", joinColumns = @JoinColumn(name = "produto_id"), inverseJoinColumns = @JoinColumn(name = "categoria_id"))
	private List<Categoria> categorias;

	@OneToOne(mappedBy = "produto")
	private Estoque estoque;

	@ElementCollection
	@CollectionTable(name = "atributo_produto", joinColumns = @JoinColumn(name = "produto_id"))
	@Column(name = "atributo")
	private List<Atributo> atributos;

	@PostLoad
	private void imprimirInformacao() {
		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		System.out.printf("#CAPTURA DATE: %s%n", dateFormat.format(LocalDateTime.now()));
	}

	@PrePersist
	private void gerandoDate() {
		if (getDataCriacao() != null)
			setDataCriacao(LocalDateTime.now());
	}

	@PreUpdate
	private void atualizandoBaseDeDados() {
		setDataUltimaAtualizacao(LocalDateTime.now());
	}
}
