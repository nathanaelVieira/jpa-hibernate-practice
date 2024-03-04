package br.com.vieira.JPQL;

import static org.junit.Assert.assertEquals;

import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import br.com.vieira.connection.EntityManagerConnectionTest;
import br.com.vieira.model.Pedido;
import br.com.vieira.model.Produto;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BuscaJPQLBasico extends EntityManagerConnectionTest {

	@Test
	public void A_buscaSimplesPeloIdentificadorJPQL() {
		TypedQuery<Pedido> typedQuery = entityManager.createQuery("""
					select p from Pedido p
					where p.id = 5
				""", Pedido.class);

		Pedido singleResult = typedQuery.getSingleResult();

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String resultFormatterDataBase = formatter.format(singleResult.getDataPedido());

		assertEquals(resultFormatterDataBase, "07/02/2024");
	}

	@Test
	public void B_observandoDiferencaEntreTypedQueryEQuery() {
		String jpqlQuery = "select p from Produto p where p.id = 7";

		// TypedQuery
		TypedQuery<Produto> query = entityManager.createQuery(jpqlQuery, Produto.class);
		List<Produto> produtos = query.getResultList();

		// Query
		Query queryPosMillennials = entityManager.createQuery(jpqlQuery);
		Produto produto = (Produto) queryPosMillennials.getSingleResult();

		assertEquals(produtos.get(0), produto);

		System.out.printf("Produto resultado: %s%n", produtos.get(0));
	}

	@Test
	public void C_buscaERetonoDeAtributo() {
		String jpqlQuery = "select (nome) from Cliente";

		TypedQuery<String> query = entityManager.createQuery(jpqlQuery, String.class);
		List<String> list = query.getResultList();

		if (!list.isEmpty())
			list.forEach(System.out::println);

		Assert.assertFalse(list.isEmpty());
	}

	@Test
	public void D_projetarResultados() {
		String jpql = "select id, nome from Cliente";
		TypedQuery<Object[]> query = entityManager.createQuery(jpql, Object[].class);
		List<Object[]> list = query.getResultList();

		list.stream().map(e -> new ClienteDTO((Integer) e[0], (String) e[1])).forEach(System.out::println);
	}

	private class ClienteDTO {
		Integer id;
		String nome;

		public ClienteDTO(Integer id, String nome) {
			this.id = id;
			this.nome = nome;
		}

		@Override
		public String toString() {
			return "IdNome [id=" + id + ", nome=" + nome + "]";
		}

	}

}
