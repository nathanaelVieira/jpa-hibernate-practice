package br.com.vieira.util;

import java.math.BigDecimal;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.vieira.model.Produto;

public class IniciarUnidadeDePersistencia {

	public static void main(String[] args) {
		boolean condicao = true;
		if (condicao) {
			EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("mysql-workbench");
			EntityManager entityManager = entityManagerFactory.createEntityManager();

			Produto produtoTest = new Produto();
			produtoTest.setNome("Produto Teste #01");
			produtoTest.setDescricao("Um produto imaginario para futuros testes.");
			produtoTest.setPreco(new BigDecimal(1000.0));

			entityManager.getTransaction().begin();
			entityManager.persist(produtoTest);
			entityManager.getTransaction().commit();
			entityManager.clear();

			Produto produto = entityManager.find(Produto.class, 1);
			System.out.println(produto.getNome());

			entityManager.close();
			entityManagerFactory.close();
		}
		System.out.println("Hello world!");
	}
}
