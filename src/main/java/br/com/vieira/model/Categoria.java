package br.com.vieira.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Entity
@Table(name = "categoria", uniqueConstraints = @UniqueConstraint(columnNames = "nome"))
public class Categoria {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(nullable = false, length = 100)
	private String nome;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "categoria_pai_id")
	private Categoria categoriaPai;

	@OneToMany(mappedBy = "categoriaPai")
	private List<Categoria> categorias;

	@ManyToMany(mappedBy = "categorias")
	private List<Produto> produtos;
}
