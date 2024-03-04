package br.com.vieira.model.dto;

public class ClienteDTO {
	private Integer id;
	private String nome;

	public ClienteDTO(Integer id, String nome) {
		this.id = id;
		this.nome = nome;
	}

	@Override
	public String toString() {
		return "ClienteDTO [id=" + id + ", nome=" + nome + "]";
	}

}
