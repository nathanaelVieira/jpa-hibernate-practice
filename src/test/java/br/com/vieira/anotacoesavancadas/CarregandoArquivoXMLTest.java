package br.com.vieira.anotacoesavancadas;

import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.List;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import br.com.vieira.connection.EntityManagerConnectionTest;
import br.com.vieira.model.Atributo;
import br.com.vieira.model.NotaFiscal;
import br.com.vieira.model.Pedido;
import br.com.vieira.model.Produto;

public class CarregandoArquivoXMLTest extends EntityManagerConnectionTest {

	@Test
	//@formatter:off
	public void carregandoArquivoXML() {

		// Cenário
		int busca = 1;
		Pedido pedido = entityManager.find(Pedido.class,busca );
		
		NotaFiscal notaFiscal = new NotaFiscal();
		notaFiscal.setDataEmissao(Calendar.getInstance().getTime());
		notaFiscal.setPedido(pedido);

		// Execução
		entityManager.getTransaction().begin();

		entityManager.persist(notaFiscal);
		notaFiscal.setXml(carregandoXML());

		entityManager.getTransaction().commit();
		entityManager.clear();

		// Verificação
		NotaFiscal buscaNotaFiscal = entityManager.find(NotaFiscal.class, notaFiscal.getId());
		Assert.assertNotNull(buscaNotaFiscal.getXml());
		Assert.assertTrue(buscaNotaFiscal.getXml().length > 0);

		FileOutputStream outputStream;
		try {
			outputStream = new FileOutputStream(
					Files
					.createFile(Paths.get(System.getProperty("user.home") + "/xml-aula.xml"))
					.toFile());
			outputStream.write(buscaNotaFiscal.getXml());
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}

	@Test
	public void salvandoImagemEmProduto() {
		
		// Cenário
		Produto produto = new Produto();
		produto.setAtributos(List.of( new Atributo("Caracteristica", "Produto teste")));
		produto.setDataCriacao(null);
		produto.setDescricao("Produto teste #F075 - Exercicio para carregamento de image.");
		produto.setEstoque(null);
		produto.setNome("Teste Image #F075");
		produto.setPreco(new BigDecimal(1000.0));
		produto.setImage(carregandoRecursos("/images.jpg"));
		
		// Execução
		entityManager.getTransaction().begin();
		entityManager.persist(produto);
		entityManager.getTransaction().commit();
		entityManager.clear();
		
		// Validação
		Produto busca = entityManager.find(Produto.class, produto.getId());
		Assert.assertNotNull(busca.getImage());
	}
	
	@Test
	@Ignore
	public void executandoQuery() {
		javax.persistence.Query query = entityManager
				.createNativeQuery("ALTER TABLE nota_fiscal MODIFY xml BLOB;");
		query.executeUpdate();
	}

	private static byte[] carregandoXML() {
		try {
			return CarregandoArquivoXMLTest.class
					.getResourceAsStream("/nota.xml").readAllBytes();
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
		return null;
	}
	
	/**
	 * Carrega recursos e transforma em um array de bytes.
	 * Pode ser usado para leitura de </br>
	 * <code style="color: blue;">Imagens, arquivos de textos, arquivos de áudio e video, etc.</code> 
	 * @param recurso
	 * @return Um Array de bytes.
	 */
	private static byte[] carregandoRecursos(String recurso) {
		try {
			return CarregandoArquivoXMLTest.class
					.getResourceAsStream(recurso).readAllBytes();
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
		return null;
	}
	//@formatter:on

}
