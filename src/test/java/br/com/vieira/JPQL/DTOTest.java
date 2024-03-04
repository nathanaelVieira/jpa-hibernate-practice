package br.com.vieira.JPQL;

import java.util.List;

import javax.persistence.TypedQuery;

import org.junit.Test;

import br.com.vieira.connection.EntityManagerConnectionTest;
import br.com.vieira.model.dto.ClienteDTO;

public class DTOTest extends EntityManagerConnectionTest {

	@Test
	public void projetarNoDTO() {
		String jpql = "select new br.com.vieira.model.dto.ClienteDTO(id, nome) from Cliente";
		TypedQuery<ClienteDTO> query = entityManager.createQuery(jpql, ClienteDTO.class);

		List<ClienteDTO> list = query.getResultList();

		list.forEach(System.out::println);
	}
}
