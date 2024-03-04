package br.com.vieira.operacaocascata;

import java.util.List;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import br.com.vieira.connection.EntityManagerConnectionTest;
import br.com.vieira.model.Categoria;
import br.com.vieira.model.Produto;

public class CascataProdutoEmCategoria extends EntityManagerConnectionTest {

	@Test
	/* Criado para executar uma unica vez */ @Ignore
	public void persistenciaProdutoCategoria() {
		// Cenário
		Produto produto = new Produto();
		produto.setNome("Produto Teste.");
		produto.setDescricao("""
				produto: teste,
				status: ativo,
				descricao: 'produto criado para teste sobre operação em cascatas'
				""");

		Categoria categoria = new Categoria();
//		categoria.setCategoriaPai(categoria);
		categoria.setNome("Teste Cascata");
		produto.setCategorias(List.of(categoria));

		// Execução
		entityManager.getTransaction().begin();
		entityManager.persist(produto);
		entityManager.getTransaction().commit();
		entityManager.clear();

		// Validação
		Categoria categoriaResgatada = entityManager.find(Categoria.class, categoria.getId());
		Assert.assertNotNull(categoriaResgatada);
	}
}
