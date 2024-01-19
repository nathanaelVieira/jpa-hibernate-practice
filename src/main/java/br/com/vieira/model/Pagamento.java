package br.com.vieira.model;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@DiscriminatorColumn(name = "DTYPE", discriminatorType = DiscriminatorType.STRING, length = 31)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "pagamento")
public abstract class Pagamento {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@MapsId
	@OneToOne(optional = false)
	@JoinColumn(name = "pedido_id")
	private Pedido pedido;

	@Enumerated(EnumType.STRING)
	private StatusPagamento status;

}

//@formatter:off
/**
       create table pagamento (
       DTYPE varchar(31) not null,
        pedido_id integer not null,
        status varchar(255),
        codigo_barras varchar(255),
        numero_cartao varchar(255),
        primary key (pedido_id)
    ) engine=InnoDB
Hibernate: 
    
    alter table pagamento 
       add constraint FKthad9tkw4188hb3qo1lm5ueb0 
       foreign key (pedido_id) 
       references pedido (id)
 */
//@formatter:on
