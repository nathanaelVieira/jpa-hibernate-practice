package br.com.vieira.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "produto", uniqueConstraints = { @UniqueConstraint(columnNames = "nome") })
public class Produto {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(columnDefinition = "varchar(215) not null")
	private String nome;

	private String descricao;

	@Column(precision = 20, scale = 2) // preco decimal(20,2)
	private BigDecimal preco;

	@Lob
	private byte[] image;

	@Column(name = "data_criacao", updatable = false)
	private LocalDateTime dataCriacao;

	@Column(name = "data_ultima_atualizacao", insertable = false)
	private LocalDateTime dataUltimaAtualizacao;

	//@formatter:off
	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
	@JoinTable(name = "produto_categoria", 
		joinColumns = @JoinColumn(name = "produto_id", foreignKey = @ForeignKey(name = "fk_produto_categorias")), 
		inverseJoinColumns = @JoinColumn(name = "categoria_id", foreignKey = @ForeignKey(name="fk_categoria_´produto")))
	private List<Categoria> categorias;
	//@formatter:on

	@OneToOne(mappedBy = "produto")
	private Estoque estoque;

	@ElementCollection
	@CollectionTable(name = "atributo_produto", joinColumns = @JoinColumn(name = "produto_id"))
	@Column(name = "atributo", nullable = false, length = 50)
	private List<Atributo> atributos;

//	@PostLoad
//	private void imprimirInformacao() {
//		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");
//		System.out.printf("#CAPTURA DATE: %s%n", dateFormat.format(LocalDateTime.now()));
//	}

	@PrePersist
	private void gerandoDate() {
		if (dataCriacao == null)
			setDataCriacao(LocalDateTime.now());
	}

	@PreUpdate
	private void atualizandoBaseDeDados() {
		setDataUltimaAtualizacao(LocalDateTime.now());
	}

	@Override
	public String toString() {
		return "Produto [id=" + id + ", nome=" + nome + ", descricao=" + descricao + ", preco=" + preco
				+ ", dataCriacao=" + dataCriacao + ", dataUltimaAtualizacao="
				+ dataUltimaAtualizacao + ", categorias=" + categorias + ", estoque=" + estoque + ", atributos="
				+ atributos + "]";
	}
	
	
}
