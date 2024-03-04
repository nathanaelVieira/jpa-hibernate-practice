package br.com.vieira.JPQL;

import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.TypedQuery;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import br.com.vieira.connection.EntityManagerConnectionTest;
import br.com.vieira.model.Cliente;
import br.com.vieira.model.Pedido;
import br.com.vieira.model.Produto;

public class JoinTest extends EntityManagerConnectionTest {

	@Test
	@Ignore
	public void executandoJoin() {
		String jpql = "select p, pag from Pedido p inner join p.pagamento pag";

		TypedQuery<Object[]> query = entityManager.createQuery(jpql, Object[].class);
		List<Object[]> list = query.getResultList();

		if (!list.isEmpty())
			list.forEach(element -> System.out.println((Pedido) element[0] + ", " + element[1]));

		assertTrue(list.isEmpty());
	}

	@Test
	@Ignore
	public void usandoJoinFetch() {

//		join fetch: significa a grosso modo, junto busque...
		String jpql = """
				select p from Produto p
				left join fetch p.categorias
				""";
		TypedQuery<Produto> query = entityManager.createQuery(jpql, Produto.class);
		List<Produto> resultList = query.getResultList();

		if (!resultList.isEmpty()) {
			resultList.forEach(element -> System.out.println(element.getCategorias() + " " + element.getAtributos()));
		}
	}

	@Test
	@Ignore
	public void buscarPedidoComProdutoEspecifico() {
		try {
			String jpql = "select ped from Pedido ped inner join fetch ped.itens.produto pro on ped.id = prod.id ";
			TypedQuery<Pedido> query = entityManager.createQuery(jpql, Pedido.class);

			List<Pedido> resultList = query.getResultList();

			resultList.forEach(pedido -> {
				pedido.getItens().forEach(produto -> {
					if (produto == null)
						System.out.println("Produto vazio");
				});
			});

		} catch (Exception e) {
			System.err.println(e.getMessage());
		}

	}

	@Test
	@Ignore
	public void buscaParametrizada() {
		int KEY = 6;
		String jpql = "select p from Produto p where p.id = ?1";
		TypedQuery<Produto> query = entityManager.createQuery(jpql, Produto.class);
		query.setParameter(1, KEY);

		List<Produto> resultList = query.getResultList();

		if (resultList.isEmpty())
			System.out.println("Vazia");

		System.out.printf("PRIMEIRO RESULTADO: %d%n", resultList.stream().collect(Collectors.counting()));
	}

	@Test
	@Ignore
	public void definindoNomesEmParametros() {
		// Sobrecarga em setParameter(position: string, value: object) permite isso. E
		// os parametros segue a convesão :nome_desejavel
		String jpql = "select p from Produto p where p.id = :idProduto";

		TypedQuery<Produto> query = entityManager.createQuery(jpql, Produto.class);
		query.setParameter("idProduto", 7);

		Produto result = query.getSingleResult();

		Assert.assertNotNull(result);
	}

	@Test
	@Ignore
	public void consultaComLike() {
		// Busca Realizada com Like TODO Procure anotar mais e estudar alguns casos...
		String jpql = "select c from Cliente c where c.nome like concat(:nome, '%') ";

		TypedQuery<Cliente> query = entityManager.createQuery(jpql, Cliente.class);
		query.setParameter("nome", "J");

		List<Cliente> list = query.getResultList();
		if (!list.isEmpty()) {
			list.forEach(cliente -> System.out.println(cliente.getNome()));
		}

		Assert.assertFalse(list.isEmpty());
	}

	@Test
	@Ignore
	public void consultaComIsNull() {
		String jpql = "select p from Produto p where p.image is null";
		TypedQuery<Produto> query = entityManager.createQuery(jpql, Produto.class);

		List<Produto> list = query.getResultList();
		Long collectNumber = list.stream().collect(Collectors.counting());
		System.out.println(collectNumber);
		Assert.assertFalse(list.isEmpty());
	}

	@Test
	public void consultaComIsEmpty() {
		String jpql = "select p from Produto p where p.categorias is empty";
		TypedQuery<Produto> query = entityManager.createQuery(jpql, Produto.class);

		List<Produto> list = query.getResultList();
		Long collectNumber = list.stream().collect(Collectors.counting());
		System.out.println(collectNumber);
		Assert.assertFalse(list.isEmpty());
	}

}
