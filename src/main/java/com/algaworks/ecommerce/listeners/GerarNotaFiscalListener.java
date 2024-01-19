package com.algaworks.ecommerce.listeners;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import br.com.vieira.model.Pedido;
import br.com.vieira.service.NotaFiscalService;

public class GerarNotaFiscalListener {

	private NotaFiscalService notaFiscalService = new NotaFiscalService();

	@PrePersist
	@PreUpdate
	private void gerar(Pedido pedido) {
		if (pedido.isPago() && pedido.getNotaFiscal() == null) {
			notaFiscalService.gerar(pedido);
		}
	}
}
