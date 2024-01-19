package br.com.vieira.anotacoesavancadas;

import java.util.Arrays;
import java.util.Collections;
//import java.util.Map;

import org.junit.Ignore;
import org.junit.Test;

import br.com.vieira.connection.EntityManagerConnectionTest;
import br.com.vieira.model.Cliente;
import br.com.vieira.model.SexoCliente;

public class ElementCollectionEmMapaTest extends EntityManagerConnectionTest {

	@Test
	@Ignore
	public void testandoPersistenciaContatosEmCliente() {
		Cliente cliente = new Cliente();
		cliente.setNome("Cliente teste #073");
		cliente.setNomeFamilia("Não necessario.");
		cliente.setPedidos(null);
		cliente.setSexo(SexoCliente.NAO_ESPECIFICADO);
		cliente.setTags(Arrays.asList("CEO", "Test aula #073"));
		cliente.setContatos(Collections.singletonMap("E-mail corporativo", "corporativo@email.com"));

//		Map<String, String> of = Map.of("Email", "corporacao@gmail.com");

		entityManager.getTransaction().begin();
		entityManager.persist(cliente);
		entityManager.getTransaction().commit();
		entityManager.clear();

		Cliente busca = entityManager.find(Cliente.class, cliente.getId());

		System.out.println(busca);

	}
}
