package br.com.vieira.model;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ForeignKey;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyColumn;
import javax.persistence.OneToMany;
import javax.persistence.PostLoad;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.swing.text.MaskFormatter;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * <h2>Cliente</h2>
 * 
 * @optinal:
 * @not-null: cpf, nome, sexo.
 */
@Getter
@Setter
@Entity
@Table(name = "cliente", uniqueConstraints = {
		@UniqueConstraint(name = "ctts_unique", columnNames = { "cpf" }) }, indexes = {
				@Index(name = "inx_nome", columnList = "nome") })
@SecondaryTable(name = "cliente_detalhe", pkJoinColumns = @PrimaryKeyJoinColumn(name = "cliente_id"))
@AllArgsConstructor
@NoArgsConstructor
public class Cliente extends EntidadeBaseInteger {

	@Column(nullable = false, length = 14)
	private String cpf;

	@Column(nullable = false, length = 100)
	private String nome;

	@Transient
	private String nomeFamilia;

	@ElementCollection
	@CollectionTable(name = "cliente_contato", joinColumns = @JoinColumn(name = "cliente_id", foreignKey = @ForeignKey(name = "fk_cliente_contados")))
	@MapKeyColumn(name = "tipo")
	@Column(name = "contato")
	private Map<String, String> contatos;

	@Enumerated(EnumType.STRING)
	@Column(table = "cliente_detalhe", nullable = false, length = 30)
	private SexoCliente sexo;

	@Column(name = "data_nascimento", table = "cliente_detalhe")
	private LocalDate dataNascimento;

	@OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
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

	public void setCpf(String cpf) {
		/*
		 * TODO Criar uma Service para cuidar do 
		 * processamento de dados nas entidades.
		 * 
		 * ClienteService.class
		 */
		try {
			MaskFormatter mask = new MaskFormatter("###.###.###-##");
			mask.setValueContainsLiteralCharacters(false);
			this.cpf = mask.valueToString(cpf);
		} catch (ParseException e) {
			this.cpf = cpf;
		}
	}

}
