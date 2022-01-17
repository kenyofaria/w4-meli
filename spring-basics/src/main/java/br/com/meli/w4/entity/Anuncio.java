package br.com.meli.w4.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

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
	
	@ManyToMany
	private List<Caracteristica> caracteristicas = new ArrayList<Caracteristica>(); 
	
	
	public void adicionaCaracteristica(Caracteristica caracteristica) {
		if(this.caracteristicas == null)
			this.caracteristicas = new ArrayList<Caracteristica>();
		this.caracteristicas.add(caracteristica);
	}
}
