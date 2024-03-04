package br.com.vieira.operacaocascata;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import br.com.vieira.connection.EntityManagerConnectionTest;
import br.com.vieira.model.Atributo;
import br.com.vieira.model.Categoria;
import br.com.vieira.model.Produto;

public class MERGEAtualizacaoCategoriaComProdutoTest extends EntityManagerConnectionTest {

	@Test
	public void deveRealizarAtualizacaoMERGE() {
		entityManager.getTransaction().begin();
		Produto produto = entityManager.find(Produto.class, 7);
		Categoria categoria = entityManager.find(Categoria.class, 4);

		produto.setDescricao("""
				A descrição de produtos é um elemento crucial no E-commerce. Ela vai além de simplesmente
				listar características básicas do produto.
				""");
		produto.getCategorias().remove(0);
		entityManager.getTransaction().commit();

		Assert.assertNotNull(produto.getCategorias().contains(categoria));

	}

	@SuppressWarnings("unused")
	private void persisteProduto() {
		Produto candidatoProduto = new Produto();
		candidatoProduto.setNome("Teste de produto 01.");
		candidatoProduto.setPreco(BigDecimal.TEN);
		candidatoProduto.setImage(carregandoRecurso("/images.jpg"));
		candidatoProduto.setDescricao("""
				produto: teste,
				descricao: teste produto. verificando a @joinColumn que coloquei na multiplicidade manyToMany.
				""");
		candidatoProduto.setAtributos(List.of(new Atributo("cor", "undefined")));
		entityManager.getTransaction().begin();
		entityManager.persist(candidatoProduto);
		entityManager.getTransaction().commit();
	}

	private static byte[] carregandoRecurso(String recursos) {
		try {
			return MERGEAtualizacaoCategoriaComProdutoTest.class.getResourceAsStream(recursos.trim()).readAllBytes();
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
		return null;
	}
}
