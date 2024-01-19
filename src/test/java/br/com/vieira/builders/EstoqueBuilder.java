package br.com.vieira.builders;

import br.com.vieira.model.Estoque;
import br.com.vieira.model.Produto;

public class EstoqueBuilder {

	private Estoque estoque;

	private static Integer contador = 0;

	private EstoqueBuilder() {
	}

	public static EstoqueBuilder umEstoque() {
		EstoqueBuilder builder = new EstoqueBuilder();
		builder.estoque = new Estoque();
		return builder;
	}

	public EstoqueBuilder comConfiguracaoPadrao() {
		estoque.setId(null);
		estoque.setQuantidade(contador);
		return this;

	}

	public EstoqueBuilder comProduto(Produto produto) {
		estoque.setProduto(produto);
		return this;
	}

	public Estoque agora() {
		return estoque;
	}

}
