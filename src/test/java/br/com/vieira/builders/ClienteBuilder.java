package br.com.vieira.builders;

import br.com.vieira.model.Cliente;
import br.com.vieira.model.SexoCliente;

public class ClienteBuilder {

	private Cliente cliente;

	private static Integer contador = 0;

	private ClienteBuilder() {
	}

	public static ClienteBuilder umCliente() {
		ClienteBuilder builder = new ClienteBuilder();
		builder.cliente = new Cliente();
		return builder;
	}

	public ClienteBuilder comConfiguracaoPadrao() {
		cliente.setNome(String.format("#%d Cliente", contador));
		contador++;
		cliente.setSexo(SexoCliente.MASCULINO);
		cliente.setPedidos(null);
		return this;
	}

	public Cliente agora() {
		return cliente;
	}

}
