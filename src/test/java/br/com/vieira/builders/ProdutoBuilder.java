package br.com.vieira.builders;

import java.math.BigDecimal;

import br.com.vieira.model.Produto;

public class ProdutoBuilder {
	private Produto produto;

	private static Integer contador = 0;

	private ProdutoBuilder() {
	}

	public static ProdutoBuilder umProduto() {
		ProdutoBuilder builder = new ProdutoBuilder();
		builder.produto = new Produto();
		return builder;
	}

	public ProdutoBuilder comConfiguracaoPadrao() {
		produto.setId(null);
		produto.setNome(String.format("#%d Produto", contador));
		produto.setDescricao("Descrição teste.");
		produto.setEstoque(EstoqueBuilder.umEstoque().comConfiguracaoPadrao().agora());
		produto.setPreco(new BigDecimal(999.99));
		return this;
	}
	public ProdutoBuilder comConfiguracaoPadraoSemEstoque() {
		produto.setId(null);
		produto.setNome(String.format("#%d Produto", contador));
		produto.setDescricao("Descrição teste.");
		produto.setEstoque(null);
		produto.setPreco(new BigDecimal(999.99));
		return this;
	}

	public Produto agora() {
		return produto;
	}
}
