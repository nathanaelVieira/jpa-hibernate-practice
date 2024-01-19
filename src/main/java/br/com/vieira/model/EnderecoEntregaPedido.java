package br.com.vieira.model;

import javax.persistence.Embeddable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Embeddable
@ToString
public class EnderecoEntregaPedido {

    private String cep;

    private String logradouro;

    private String numero;

    private String complemento;

    private String bairro;

    private String cidade;

    private String estado;
}
