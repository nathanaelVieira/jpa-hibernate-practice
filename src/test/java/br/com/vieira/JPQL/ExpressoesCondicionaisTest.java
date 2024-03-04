package br.com.vieira.JPQL;

import java.util.List;

import javax.persistence.TypedQuery;

import org.junit.Assert;
import org.junit.Test;

import br.com.vieira.connection.EntityManagerConnectionTest;
import br.com.vieira.model.Cliente;
import br.com.vieira.model.Pedido;

public class ExpressoesCondicionaisTest extends EntityManagerConnectionTest {

	@Test
	public void clausulaIN() {
		String jpql = "select p from Pedido p where p.id in (4,5)";

		TypedQuery<Pedido> query = entityManager.createQuery(jpql, Pedido.class);

		List<Pedido> resultList = query.getResultList();

		resultList.forEach(element -> {
			System.out.println(element);
		});

		Assert.assertNotNull(resultList.isEmpty());
	}

	@Test
	public void clausulaIN_Parameter() {
		Cliente cliente1 = entityManager.find(Cliente.class, 9);
		Cliente cliente2 = entityManager.find(Cliente.class, 10);

		List<Cliente> list = List.of(cliente1, cliente2);

		String jpql = "select p from Pedido p where p.cliente in (:list)";

		TypedQuery<Pedido> query = entityManager.createQuery(jpql, Pedido.class);
		query.setParameter("list", list);

		List<Pedido> resultList = query.getResultList();

		resultList.forEach(element -> {
			System.out.println(element);
		});

		Assert.assertNotNull(resultList.isEmpty());
	}

}
