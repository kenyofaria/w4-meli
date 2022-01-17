package br.com.meli.w4.entity;

import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Embeddable
public class Endereco {
	
	private String logradouro;
	private Integer numero;
	private String bairro;
	private String uf;
	private String cidade;
}
