package br.com.meli.w4.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.meli.w4.entity.Usuario;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDTO {

	private String nome;
	private LocalDate dataNascimento;
	private char sexo;
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String senha;
	private LocalDate dataCriacao;
	
	
	
	public static Usuario converte(UsuarioDTO dto) {
		Usuario usuario = Usuario.builder()
			.nome(dto.getNome())
			.dataNascimento(dto.getDataNascimento())
			.sexo(dto.getSexo())
			.senha(dto.getSenha())
			.dataCriacao(LocalDate.now())
			.build();
		
		return usuario;
	}
	
	public static UsuarioDTO converte(Usuario usuario) {
		return UsuarioDTO.builder()
			.nome(usuario.getNome())
			.dataNascimento(usuario.getDataNascimento())
			.sexo(usuario.getSexo())
			.senha(usuario.getSenha())
			.dataCriacao(usuario.getDataCriacao())
			.build();
	}
	
}
