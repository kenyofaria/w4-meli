package br.com.meli.w4.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Anuncio {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String codigo;
	private String titulo;
	private String categoria;
	private BigDecimal preco;
	@ManyToOne
	private Vendedor vendedor;
	
	@Transient
	private List<Caracteristica> caracteristicas = new ArrayList<Caracteristica>(); 

	public void adicionaCaracteristica(Caracteristica caracteristica, String valor) {
		if(this.caracteristicas == null)
			this.caracteristicas = new ArrayList<Caracteristica>();
		caracteristica.setValor(valor);
		this.caracteristicas.add(caracteristica);
	}
}
