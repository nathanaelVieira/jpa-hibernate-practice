package br.com.vieira.service;

import br.com.vieira.model.Pedido;

@SuppressWarnings("unused")
public class NotaFiscalService {

	private final String VERDE = "\u001B[32m";
	private final String VERMELHO = "\u001B[31m";
	private final String AZUL = "\u001B[34m";
	private final String RESETAR = "\u001B[0m";

	public void gerar(Pedido pedido) {
		System.out.println(VERDE + "Gerando nota para o pedido : " + RESETAR + pedido.getId());
	}

}
