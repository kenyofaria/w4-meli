package br.com.meli.w4.entity;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Anuncio {

	private int id;
	private String codigo;
	private String titulo;
	private String categoria;
	private BigDecimal preco;
}
