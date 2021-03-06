package br.com.meli.w4.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Imovel {

	@Id
	private Integer numero;
	@ManyToOne
	private Bairro bairro;
	@OneToMany
	private List<Comodo> comodos;
}
