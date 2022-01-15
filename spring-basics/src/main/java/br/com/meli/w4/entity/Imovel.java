package br.com.meli.w4.entity;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Imovel {

	private Integer numero;
	private Bairro bairro;
	private List<Comodo> comodos;
}
