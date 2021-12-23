package br.com.meli.w4.entity;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Usuario {

	@JsonIgnore
	private Long id;
	private String nome;
	private LocalDate dataNascimento;
	private char sexo;
	private LocalDate dataCriacao;
	private String senha;
	
}
