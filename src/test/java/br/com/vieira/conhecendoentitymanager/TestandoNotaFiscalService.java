package br.com.vieira.conhecendoentitymanager;

import static org.mockito.ArgumentMatchers.any;

import java.time.LocalDateTime;

import org.junit.Assert;
import org.junit.Assume;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import br.com.vieira.connection.EntityManagerConnectionTest;
import br.com.vieira.model.Cliente;
import br.com.vieira.model.Pedido;
import br.com.vieira.model.StatusPedido;
import br.com.vieira.service.NotaFiscalService;

public class TestandoNotaFiscalService extends EntityManagerConnectionTest {

	private boolean RECURSOS_LIGADOS = false;

	@Spy
//	@Mock
	private NotaFiscalService service;

	@Mock
	private Pedido pedido;

	@Mock
	private Cliente cliente;

	@Test
//	@Ignore
	public void deveRetornarAImpressao() {
		MockitoAnnotations.openMocks(this);
		service.gerar(pedido);
		Mockito.verify(service).gerar(any());
		Mockito.verifyNoMoreInteractions(service);
	}

	@Test
	public void deveAvisarOsOuvintes() {
		Assume.assumeTrue(RECURSOS_LIGADOS);

		MockitoAnnotations.openMocks(this);

		Pedido pedido = new Pedido();
		pedido.setDataPedido(LocalDateTime.now());
		pedido.setDataConclusao(LocalDateTime.of(2024, 1, 1, 17, 48));
		pedido.setEnderecoEntrega(null);
		pedido.setCliente(cliente);
		pedido.setStatus(StatusPedido.PAGO);

		entityManager.getTransaction().begin();
		entityManager.persist(cliente);
		entityManager.persist(pedido);
		entityManager.getTransaction().commit();

		Pedido pedido2 = entityManager.find(Pedido.class, pedido.getId());

		Assert.assertNotNull(pedido2);
	}

}
